package eu.ttbox.batch.techdata;

import java.util.ArrayList;
import java.util.List;

public enum TechDataFeedEnum {

	techMat("/jobs/techdata-material.xml", "materialTechdataJob"), //
	techKit("/jobs/techdata-kit.xml", "kitTechdataJob"), //
	techStock("/jobs/techdata-stock.xml", "stockTechdataJob"), //
	techTax("/jobs/techdata-tax.xml", "taxTechdataJob"), //
	techAddr("/jobs/techdata-shipToRs.xml", "shipToRsTechdataJob"), //
	techPrice("/jobs/techdata-price.xml", "priceTechdataJob");

	private final String jobPath;

	private final String jobIdentifier;

	TechDataFeedEnum(String fileName, String jobIdentifier) {
		this.jobPath = fileName;
		this.jobIdentifier = jobIdentifier;
	}

	public String getJobPath() {
		return jobPath;
	}

	public String getJobIdentifier() {
		return jobIdentifier;
	}

	public boolean isValidService() {
		if (this.jobPath == null) {
			return false;
		}
		if (this.jobIdentifier == null) {
			return false;
		}

		return true;
	}

	public static List<TechDataFeedEnum> getValidEnums() {
		List<TechDataFeedEnum> results = new ArrayList<TechDataFeedEnum>();
		TechDataFeedEnum[] values = TechDataFeedEnum.values();
		for (TechDataFeedEnum val : values) {
			if (val.isValidService()) {
				results.add(val);
			}
		}
		return results;
	}

}
