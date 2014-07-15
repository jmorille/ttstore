package eu.ttbox.batch.core.download.http;

import org.apache.http.*;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class GzipHttpResponseInterceptor implements HttpResponseInterceptor {

	@Override
	public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			Header ceheader = entity.getContentEncoding();
			if (ceheader != null) {
				HeaderElement[] codecs = ceheader.getElements();
				for (int i = 0; i < codecs.length; i++) {
					if (codecs[i].getName().equalsIgnoreCase("gzip")) {
						response.setEntity(new GzipDecompressingEntity(response.getEntity()));
						return;
					}
				}
			}
		}
	}

}
