package eu.ttbox.batch.ingram;

import java.util.ArrayList;
import java.util.List;

public enum IngramFeedEnum {

	price("/jobs/ingram-price.xml", "priceIngramJob");

	private final String jobPath;

	private final String jobIdentifier;

	IngramFeedEnum(String fileName, String jobIdentifier) {
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

	public static List<IngramFeedEnum> getValidEnums() {
		List<IngramFeedEnum> results = new ArrayList<IngramFeedEnum>();
		IngramFeedEnum[] values = IngramFeedEnum.values();
		for (IngramFeedEnum val : values) {
			if (val.isValidService()) {
				results.add(val);
			}
		}
		return results;
	}

}
