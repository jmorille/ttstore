package eu.ttbox.batch.icecat.product.update;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.index.get.GetField;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class EsIcecatLinkVO {

	final String productId;
	
	final ListenableActionFuture<GetResponse> indexAllGetResponse;
	
	final Map<String, Object> source;
	
	public static final String PATH_VERSION_DATE = EsIndexFieldEnum.pathVersionDate.name();
	
	public EsIcecatLinkVO( String productId, Map<String, Object> source, ListenableActionFuture<GetResponse> indexAllGetResponse) {
		super();
		this.productId = productId;
		this.source = source;
		this.indexAllGetResponse = indexAllGetResponse;
	}
 
	public GetResponse indexAllGetResponse(long timeoutInMs) throws InterruptedException, ExecutionException, TimeoutException  {
		GetResponse response = indexAllGetResponse.get(timeoutInMs, TimeUnit.MILLISECONDS);
		return response;
	}
	
	public Map<String, Object> getSource() {
		return source;
	} 
 
 
	public Date getPathVersionDate() {
		Date fileUpdatedDate = new Date((Long) source.get(PATH_VERSION_DATE)); 
		return fileUpdatedDate;
	}
	

	public Date getIndexAllPathVersionDate(long timeoutInMs) throws InterruptedException, ExecutionException, TimeoutException {
		GetResponse response =indexAllGetResponse(timeoutInMs);
		GetField pathVersionDateField = response.getFields().get(PATH_VERSION_DATE);
		Date fileUpdatedDate = new Date((Long)  pathVersionDateField.getValue());
		return fileUpdatedDate;
	}
	
	

}
