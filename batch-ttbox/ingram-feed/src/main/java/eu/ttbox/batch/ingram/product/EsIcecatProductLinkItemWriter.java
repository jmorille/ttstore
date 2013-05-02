package eu.ttbox.batch.ingram.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.base.Strings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import eu.ttbox.model.product.CatalogSrcEnum;
import eu.ttbox.model.product.Product;

public class EsIcecatProductLinkItemWriter implements ItemWriter<Product> {

	private static final Logger LOG = LoggerFactory.getLogger(EsIcecatProductLinkItemWriter.class);

	@Autowired
	Client esClient;

	@Value("${es.icecat.all.indexName}")
	String allIndexName;

	long timeoutMillis = 1000;

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		Assert.hasText(allIndexName, "allIndexName is mandatory");
		Assert.notNull(esClient, "esClient is mandatory");
	}

	@Override
	public void write(List<? extends Product> items) throws Exception {

	}

	public void write(Product... items) throws Exception {
		List<Product> toUpdate = new ArrayList<Product>(items.length);

		for (Product product : items) {
			boolean isSame = true;
			String icecatId = product.getExtIcecatId();
			if (Strings.isNullOrEmpty(icecatId)) {
				// Search Link
				String ean = product.getEan();
				if (!Strings.isNullOrEmpty(ean)) {
					TermQueryBuilder query = QueryBuilders.termQuery("eans", ean);
					ListenableActionFuture<SearchResponse> responseWaiting = esClient.prepareSearch(allIndexName).setQuery(query).setSize(1).execute();
					SearchResponse response = responseWaiting.actionGet(timeoutMillis);
					SearchHits hits = response.hits();
					if (hits.totalHits() == 1) {
						SearchHit hit = hits.hits()[0];
						icecatId = hit.getId();
						product.addSrc(CatalogSrcEnum.ICECAT, icecatId, "BY_EAN");
						toUpdate.add(product);
					} else if (hits.totalHits() > 1) {
						LOG.warn("Could not associate ean {} and found {} potential Icecat Product", ean, hits.totalHits());
					} else {
						LOG.debug("Could not associate ean {} to Icecat from {}", ean, product);
					}
				}
			}
		}
	}

}
