package eu.ttbox.batch.icecat.product.update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.action.get.GetRequestBuilder;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.get.GetField;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EsIcecatProductItemrReader implements ItemReader<EsIcecatLinkVO>, ItemStream {

	private static final Logger LOG = LoggerFactory.getLogger(EsIcecatProductItemrReader.class);

	@Autowired
	Client esClient;

	@Value("${es.icecat.indexName}")
	String indexName;

	@Value("${es.icecat.all.indexName}")
	String allIndexName;

	@Value("${es.icecat.all.indexType}")
	String allIndexType;

	int pageSize = 200;

	private SearchResponse scrollResp;

	private Long searchResponseTotalHits;

	private ListItemReader<EsIcecatLinkVO> delegate;

	private long currentHit = 0;

	private long beginTimeInMs;

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		// QueryBuilder query = QueryBuilders.termQuery("multi", "test");
		currentHit = 0;
		beginTimeInMs = System.currentTimeMillis();
		scrollResp = esClient.prepareSearch(indexName) //
				.setSearchType(SearchType.SCAN) //
				.setScroll(new TimeValue(60000)) //
				// .setQuery(query.buildAsBytes())
				.setSize(pageSize).execute().actionGet();
		searchResponseTotalHits = Long.valueOf(scrollResp.getHits().totalHits());
		LOG.info("Starting Es scroll of {} items", searchResponseTotalHits);

	}

	@Override
	public EsIcecatLinkVO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		EsIcecatLinkVO result = null;
		if (delegate != null) {
			result = delegate.read();
		}

		if (result == null && scrollResp != null) {
			// Manage Log count
			if (LOG.isInfoEnabled()) {
				// Enlapse time
				long endTime = System.currentTimeMillis();
				long deltaTime = endTime - beginTimeInMs;
				TimeValue currentTimeValue = new TimeValue(deltaTime);
				// Expected time
				TimeValue estimatedTimeValue = null;
				if (currentHit > 0) {
					long estimatedTime = (deltaTime * (searchResponseTotalHits.longValue() - currentHit) / currentHit);
					estimatedTimeValue = new TimeValue(estimatedTime);
				}
				LOG.info("Read {} / {} in {} (end estimated in {})", new Object[] { currentHit, searchResponseTotalHits, currentTimeValue, estimatedTimeValue });
			}
			// Do follow search
			scrollResp = esClient.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
			boolean hitsRead = false;
			List<EsIcecatLinkVO> listHits = new ArrayList<EsIcecatLinkVO>();
			for (SearchHit hit : scrollResp.getHits()) {
				if (hitsRead) {
					listHits.add(getEsIcecatLinkVO(hit));
				} else {
					hitsRead = true;
					result = getEsIcecatLinkVO(hit);
				}
			}
			delegate = new ListItemReader<EsIcecatLinkVO>(listHits);
			if (!hitsRead) {
				scrollResp = null;
			}
		}
		if (result == null) {
			// Enlapse time
			long endTime = System.currentTimeMillis();
			long deltaTime = endTime - beginTimeInMs;
			TimeValue currentTimeValue = new TimeValue(deltaTime);
			LOG.info("Read {} / {} End in {}", new Object[] { currentHit, searchResponseTotalHits, currentTimeValue });
		}
		currentHit++;
		return result;
	}

	private EsIcecatLinkVO getEsIcecatLinkVO(SearchHit hit) {
		ListenableActionFuture<GetResponse> indexAllVersion = getAllIndexPathVersion(hit.getId());
		EsIcecatLinkVO vo = new EsIcecatLinkVO(hit.getId(), hit.sourceAsMap(), indexAllVersion);
		return vo;
	}

	private ListenableActionFuture<GetResponse> getAllIndexPathVersion(String productId) {
		GetRequestBuilder request = esClient.prepareGet(allIndexName, allIndexType, productId);
		request.setFields(EsIcecatLinkVO.PATH_VERSION_DATE);
		ListenableActionFuture<GetResponse> response = request.execute();
		return response;
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {

	}

	@Override
	public void close() throws ItemStreamException {
		scrollResp = null;
		searchResponseTotalHits = null;
		currentHit = 0;

	}

}
