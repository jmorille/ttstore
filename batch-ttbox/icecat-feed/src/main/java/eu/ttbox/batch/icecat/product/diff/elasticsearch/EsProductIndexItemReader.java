package eu.ttbox.batch.icecat.product.diff.elasticsearch;

import eu.ttbox.icecat.model.product.IcecatProduct;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;

public class EsProductIndexItemReader implements ItemReader<IcecatProduct>, ItemStream {

	private Client esClient;

	private String indexName;

	private String indexType;

	private SearchResponse scrollResp;

	private ListItemReader<SearchHit> delegate;

	public void setEsClient(Client elasticClient) {
		this.esClient = elasticClient;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		// QueryBuilder query = QueryBuilders.termQuery("multi", "test");
		//
		// scrollResp = esClient.prepareSearch(indexName) //
		// .setSearchType(SearchType.SCAN) //
		// .setScroll(new TimeValue(60000)) //
		// // .setQuery(query.buildAsBytes())
		// .setSize(100).execute().actionGet();

	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws ItemStreamException {
		scrollResp = null;

	}

	@Override
	public IcecatProduct read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// SearchHit result = delegate.read();
		// if (result == null && scrollResp != null) {
		// scrollResp = esClient.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
		// boolean hitsRead = false;
		// List<SearchHit> listHits = new ArrayList<SearchHit>();
		// for (SearchHit hit : scrollResp.getHits()) {
		// if (hitsRead) {
		// listHits.add(hit);
		// } else {
		// hitsRead = true;
		// result = hit;
		// }
		// }
		// delegate = new ListItemReader<SearchHit>(listHits);
		// if (!hitsRead) {
		// scrollResp = null;
		// }
		// }
		// // TODO Manage Search Hit
		// if (result != null) {
		// // return
		// }
		return null;
	}

}
