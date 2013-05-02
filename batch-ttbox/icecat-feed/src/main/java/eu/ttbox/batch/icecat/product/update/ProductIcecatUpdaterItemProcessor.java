package eu.ttbox.batch.icecat.product.update;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.action.get.GetRequestBuilder;
import org.elasticsearch.common.base.Strings;
import org.elasticsearch.index.get.GetField;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import biz.icecat.referential.v1.Product;
import eu.ttbox.batch.core.fs.ProxyCacheDownloadConnector;
import eu.ttbox.batch.icecat.product.detail.model.ProductSynchroniser;
import eu.ttbox.batch.icecat.product.detail.parser.IcecatProductDetailFileUnmarshalliser;
import eu.ttbox.icecat.model.product.IcecatProduct;

@Service
public class ProductIcecatUpdaterItemProcessor implements ItemProcessor<EsIcecatLinkVO, IcecatProduct> {

	private static final Logger LOG = LoggerFactory.getLogger(ProductIcecatUpdaterItemProcessor.class);

	@Autowired
	Client esClient;

	@Value("${es.icecat.all.indexName}")
	String indexName;

	@Value("${es.icecat.all.indexType}")
	String indexType;

	@Autowired
	ProxyCacheDownloadConnector icecatProxyCacheHttpConnector;

	@Autowired
	IcecatProductDetailFileUnmarshalliser productUmarshalliser;

	@Autowired
	@Qualifier("icecatProductSynchroniser")
	ProductSynchroniser productSynchroniser;

	long timeoutInMs = 60000;

	private static final String PATH_VERSION_DATE = EsIndexFieldEnum.pathVersionDate.name();
	
	@Override
	public IcecatProduct process(EsIcecatLinkVO item) throws Exception {
		Map<String, Object> source = item.getSource();
		String relativePath = (String) source.get(EsIndexFieldEnum.path.name());
		// Check Validity
		if (Strings.isNullOrEmpty(relativePath)) {
			LOG.warn("No path for product id {}", item.productId);
			return null;
		}
		//
		Date fileUpdatedDate = item.getPathVersionDate(); // TODO Date
		Date allFileUpdatedDate = item.getIndexAllPathVersionDate(timeoutInMs);
		boolean download = true;
		if (fileUpdatedDate!=null && allFileUpdatedDate!=null && fileUpdatedDate.compareTo(allFileUpdatedDate) >= 0) {
			download = false;
		}
		try {
			// Download file
			File icecatFile = icecatProxyCacheHttpConnector.downloadFile(relativePath, fileUpdatedDate);
			Product productFile = productUmarshalliser.convertFileToProduct(icecatFile);
			// Manage Synchro
			IcecatProduct entity = new IcecatProduct();
			entity = productSynchroniser.updateIcecatProductWithAllDependencies(entity, productFile);
			return entity;
		} catch (FileNotFoundException e) {
			LOG.warn("Ignore {} for Not Found Error: {} ", relativePath, e.getMessage());
		}
		return null;
	}

 

}
