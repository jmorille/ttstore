package eu.ttbox.server.admin.server.model;

import java.util.List;

import eu.ttbox.model.salespoint.Salespoint;

public class SearchResult  {
 
	private   List<Salespoint> hits;
	
	private   int hitsCount;
	
	private   int start;
	
	private   int pageSize;

	public SearchResult() {
	    super();
	}

	public SearchResult(List<Salespoint> hits, int hitsCount, int start, int pageSize) {
	    super();
	    this.hits = hits;
	    this.hitsCount = hitsCount;
	    this.start = start;
	    this.pageSize = pageSize;
    }

	public  List<Salespoint> getHits() {
    	return hits;
    }

	public int getHitsCount() {
    	return hitsCount;
    }

	public int getStart() {
    	return start;
    }

	public int getPageSize() {
    	return pageSize;
    }
	

	
	public void setHits(List<Salespoint> hits) {
    	this.hits = hits;
    }

	public void setHitsCount(int hitsCount) {
    	this.hitsCount = hitsCount;
    }

	public void setStart(int start) {
    	this.start = start;
    }

	public void setPageSize(int pageSize) {
    	this.pageSize = pageSize;
    }

	@Override
	public String toString() {
		return "SearchResult [hits=" + hits.size() + ", hitsCount=" + hitsCount + ", start=" + start + ", pageSize=" + pageSize + "]";
	}
	
	
	
}
