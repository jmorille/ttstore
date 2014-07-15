package eu.ttbox.batch.core.download.http;

import com.google.common.base.Strings;
import eu.ttbox.batch.core.download.DownloaderConnector;
import eu.ttbox.batch.core.fs.FileUtils;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class HttpConnector implements DownloaderConnector {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public static final String DEFAULT_ENCODING = "UTF-8";

	private String encoding = DEFAULT_ENCODING;

	boolean isLastModifiedSinceDefault = true;

	private String username;

	private String password;

	private String hostname;

	private int httpPort = -1;

	private int delayWaitInMs = 0; // 500,1000

	int bufferCachSize = 20480*2;
	
	// Internal
	private long lastAccess = 0;

	private HttpHost targetHost;

	HttpHost proxy;// = new HttpHost("webcache.generali.fr", 3128, "http");

	private String proxyHostname;

	private int proxyPort = -1;

//    http.proxyHost: the host name of the proxy server
//    http.proxyPort: the port number, the default value being 80.
//    http.nonProxyHosts: a list of hosts that should be reached directly, bypassing the proxy. This is a list of patterns separated by '|'. The patterns may start or end with a '*' for wildcards. Any host matching one of these patterns will be reached through a direct connection instead of through a proxy.
//	System.getProperties().put( "socksProxyPort", "1080");
//    System.getProperties().put( "socksProxyHost" ,"proxy.host.address");
    
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setLastModifiedSinceDefault(boolean isLastModifiedSinceDefault) {
		this.isLastModifiedSinceDefault = isLastModifiedSinceDefault;
	}

	public void setUsername(String login) {
		this.username = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDelayWaitInMs(int delayWaitInMs) {
		this.delayWaitInMs = delayWaitInMs;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	 

	public void setProxyHostname(String proxyHostname) {
		this.proxyHostname = proxyHostname;
	}
 
	public void setProxyPort(String proxyPort) {
		if (proxyPort!=null && proxyPort.length()>0) {
			this.proxyPort = Integer.valueOf(proxyPort);
		}
	}

	@PostConstruct
	public void afterPropertySet() {
		Assert.notNull(hostname, "hostname is mandatory");
		targetHost = new HttpHost(hostname, httpPort, "http");
		if (!Strings.isNullOrEmpty(proxyHostname)) {
			proxy = new HttpHost(proxyHostname, proxyPort, "http");
		}

	}

	@PreDestroy
	public void destroy() {
		getHttpClient().getConnectionManager().shutdown();
	}

	private HttpContext getHttpContext() {
		// preemptively using BASIC scheme.
		// ----------------------------------

		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(targetHost, basicAuth);

		// Add AuthCache to the execution context
		BasicHttpContext localcontext = new BasicHttpContext();
		localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
		return localcontext;
	}

	private HttpClient getHttpClient() {
		return getHttpClient(username, password);
	}

	private HttpClient getHttpClient(String user, String pass) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		// Proxy
		if (proxy != null) {
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}

		// Credential
		if (!Strings.isNullOrEmpty(user) && !Strings.isNullOrEmpty(pass)) {
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(targetHost.getHostName(), targetHost.getPort()),
					new UsernamePasswordCredentials(user, pass));
		}
		// Add as the first request interceptor
		// ----------------------------------
		// Param
		httpClient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");

		// Interceptor
		httpClient.addRequestInterceptor(new GzipHttpRequestInterceptor());
		httpClient.addResponseInterceptor(new GzipHttpResponseInterceptor());

		return httpClient;
	}

	/**
	 * Ask To Wait the Wanting Delay
	 * 
	 * @return The System.currentTimeMillis()
	 * @throws InterruptedException
	 */
	private long askToWaitForWantedDelay() {
		// On fait une petite pause
		long now = System.currentTimeMillis();
		if (delayWaitInMs > 0) {
			long deltaDelay = now - lastAccess;
			if (deltaDelay < delayWaitInMs) {
				long waitMs = delayWaitInMs - deltaDelay;
				logger.debug("Need to Wait {} ms because only pass {} ms.", waitMs, deltaDelay);
				// Thread.currentThread().sleep(waitMs)
				try {
					Thread.sleep(waitMs);
				} catch (InterruptedException e) {
					logger.error("Error during askToWaitForWantedDelay : " + e.getMessage(), e);
				}
			}
			// else {
			// logger.debug("No Need to Wait last access in {} ms." , deltaDelay
			// );
			// }
		}
		return now;
	}

	@Override
	public File downloadFile(String relativePath, File destFile) throws IOException {
		// Construct Url
		String hostString = targetHost.toURI();
		boolean addSep = !relativePath.startsWith("/");
		int bufSize = addSep ? hostString.length() + relativePath.length() + 1 : hostString.length()
				+ relativePath.length();
		StringBuffer sb = new StringBuffer(bufSize);
		sb.append(hostString);
		if (addSep) {
			sb.append("/");
		}
		sb.append(relativePath);
		String url = sb.toString();
		return copyUrlToFile(url, destFile);
	}

	private File copyUrlToFile(String url, File destFile) throws IOException {
		return copyUrlToFile(url, destFile, isLastModifiedSinceDefault);
	}


	private File copyUrlToFile(String url, File destFile, boolean isLastModifiedSince) throws IOException {
		// Create parent
		boolean destFilePreExist = destFile.exists();
		if (!destFilePreExist) {
			File parentDestFile = destFile.getParentFile();
			parentDestFile.mkdirs();
		}
		// Sun, 06 Nov 1994 08:49:37 GMT ; RFC 822, mis à jour par la RFC 1123
		SimpleDateFormat dateHttpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		dateHttpDateFormat.setTimeZone(FileUtils.GMT);

		// On fait une petite pause
		long now = askToWaitForWantedDelay();

		// Init Http Connection
		HttpClient httpClient = getHttpClient();
		HttpGet httpget = new HttpGet(url);

		// IF_MODIFIED_SINCE
		if (destFilePreExist && isLastModifiedSince) {
			String lastModifiedString = dateHttpDateFormat.format(getLocalFileDate(destFile));
			httpget.addHeader(HttpHeaders.IF_MODIFIED_SINCE, lastModifiedString);
			if (logger.isDebugEnabled()) {
				logger.debug("Need to get {} to Update {} (LastModified : {})", new Object[] { url, destFile,
						lastModifiedString });
			}
		} else {
			logger.debug("Need to get {} to Create {}", url, destFile);
		}

		try {

			SimpleDateFormat sfForLog =  FileUtils.getDateFormatForLog(); 
		
			
			// Do Http Request
			// logger.debug("RequestLine : {} ", httpget.getRequestLine());
			HttpResponse response = httpClient.execute(httpget, getHttpContext());
			// Manage Response
			StatusLine responseStatusLine = response.getStatusLine();
			logger.debug("Response code {} : {}", responseStatusLine.getStatusCode(),
					responseStatusLine.getReasonPhrase());
			if (responseStatusLine.getStatusCode() == HttpStatus.SC_NOT_MODIFIED) {
				httpget.abort();
				return destFilePreExist ? destFile : null;
			} else if (responseStatusLine.getStatusCode() != HttpStatus.SC_OK) {
				httpget.abort();
				// HTTP/1.0 502 Bad Gateway
				throw new FileNotFoundException("Url " + url + " does not exists : reponse code " + responseStatusLine);
			} else {
				// Read Header Last-Modified
				Date headerLastModifiedDate = getHeaderLastModifiedDate(response, dateHttpDateFormat);
				if (destFilePreExist && headerLastModifiedDate != null) {
					if (destFile.lastModified() == headerLastModifiedDate.getTime()) {
						httpget.abort();
						if (logger.isInfoEnabled()) {
							logger.info("Use local file {} with same Date Version (Last-Modified {})",destFile, 
									sfForLog.format(headerLastModifiedDate));
						}
						return destFile;
					}
				}
				// --- Consume Response
				// ---------------------
				String responseCharSet = encoding; // EntityUtils.getContentCharSet(entity)
													// entity.getContentEncoding().getValue();
				File tmp = new File(destFile.getParentFile(), "~tmp-" + destFile.getName());
				if (tmp.exists()) {
					tmp.delete();
				}
				try {
					long beginDownload = System.currentTimeMillis();
					HttpEntity entity = response.getEntity();
					logger.debug("Response ContentCharSet {}  / ContentEncoding {}",
							EntityUtils.getContentCharSet(entity), entity.getContentEncoding());
					copyHttpEntity(response, url, tmp);
					 
					httpget.abort();
					// EntityUtils.consume(entity);
					long endDownload = System.currentTimeMillis();
					if (logger.isInfoEnabled()) {
						String sfFormat = sfForLog.format(headerLastModifiedDate);
						logger.info("Download file {} \t in {} ms. \t (Date : {})", new Object[] { destFile, (endDownload - beginDownload), sfFormat } );
					}
					// Rename To Dest File
					FileUtils.renameFileTo(destFile, tmp, headerLastModifiedDate);
				} finally {
					tmp.delete();
				}

			}
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			// Do not feel like reading the response body
			// Call abort on the request object
			httpget.abort();
			return null;
		} finally {
			// Save MArker
			lastAccess = System.currentTimeMillis();
			httpget.abort();
		}
		return destFile;
	}

	private Date getLocalFileDate(File destFile) {
//		Calendar cal = Calendar.getInstance();
//		TimeZone timezoneGMT = TimeZone.getTimeZone("GMT");
//		cal.setTimeZone(timezoneGMT);
//		cal.setTimeInMillis(destFile.lastModified());
//		Date fileDate= cal.getTime();
		Date fileDate = new Date(destFile.lastModified());
		return fileDate;
	}


	
	private void copyHttpEntity(HttpResponse response, String url, File tmp) throws IOException {
		
		String responseCharSet = encoding; // EntityUtils.getContentCharSet(entity)
											// entity.getContentEncoding().getValue();
		HttpEntity entity = response.getEntity();
		InputStream is = new BufferedInputStream(entity.getContent(), bufferCachSize);
		try {
			// Compute Method copy Method
			boolean writeAsBinary = true;
			if (FileUtils.isTextFile(tmp) || FileUtils.isTextFileName(url)) {
				writeAsBinary = false;
			}
			// Do Copy
			if (writeAsBinary) {
				writeRequestAsBinary(is, tmp);
			} else {
				if (FileUtils.isGzipFileName(url)) {
					is = new GZIPInputStream(is, bufferCachSize);
				}
				// Copy File line by line
				writeRequestAsWriter(is, tmp, responseCharSet, encoding);
			}
		} finally {
			is.close();
		}
	}



	private Date getHeaderLastModifiedDate(HttpResponse response, SimpleDateFormat dateHttpDateFormat) {
		Date headerLastModifiedDate = null;
		Header header = response.getLastHeader(HttpHeaders.LAST_MODIFIED);
		if (header != null) {
			try {
				String headerValue =  header.getValue();
				logger.debug("Response HttpHeader {} with value : {}", header.getName(), headerValue);
				headerLastModifiedDate = dateHttpDateFormat.parse(headerValue);
				if (logger.isDebugEnabled()) {
					SimpleDateFormat sfForLog = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z"); 
					sfForLog.setTimeZone(TimeZone.getTimeZone("GMT"));
					logger.debug("Response HttpHeader LAST_MODIFIED \"{}\" converted to {}",headerValue, sfForLog.format(headerLastModifiedDate));
				}
			} catch (ParseException e) {
				logger.error("Error Parsing date " + header.getValue() + " : " + e.getMessage(), e);
			}
		}
		return headerLastModifiedDate;
	}

	public void writeRequestAsWriter(InputStream is, File destFile, String responseCharSet, String destFilEncoding)
			throws IOException {
		// Force Writing Encoding To UTF-8
		logger.debug("Copy To File {} with encoding ={}", destFile, destFilEncoding);
 
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, responseCharSet), bufferCachSize);
		try {
			OutputStream out = new BufferedOutputStream(new FileOutputStream(destFile), bufferCachSize);
			if (FileUtils.isGzipFile(destFile)) {
				out = new GZIPOutputStream(out, bufferCachSize);
			}
			Writer fw = new BufferedWriter(new OutputStreamWriter(out, destFilEncoding));
			try {
				String line = null;
				while ((line = reader.readLine()) != null) {
					fw.write(line + "\n");
				}
			} finally {
				fw.flush();
				fw.close();
			}
			out.flush();
			out.close();
		} finally {
			// Close Reading Stream
			reader.close();
		}
	}

	public void writeRequestAsBinary(InputStream in, File destFile) throws IOException { 
		OutputStream out = new BufferedOutputStream(new FileOutputStream(destFile), bufferCachSize);
		try {
			writeRequestAsBinary(in, out);
		} finally {
			out.flush();
			out.close();
		}
	}

	public void writeRequestAsBinary(InputStream in, OutputStream out) throws IOException {
		int l;
		byte[] buffer = new byte[2048];
		while ((l = in.read(buffer)) != -1) {
			out.write(buffer, 0, l);
		}
	}

}