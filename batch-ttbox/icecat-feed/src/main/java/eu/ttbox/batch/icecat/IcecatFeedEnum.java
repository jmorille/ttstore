package eu.ttbox.batch.icecat;

import java.util.ArrayList;
import java.util.List;

public enum IcecatFeedEnum {

	initEs("jobs/icecat-index-mapping.xml", "icecatEsIndexCreateJob", IcecatFeedKindEnum.INIT), //
	icecatProductSync("jobs/product-sync-es.xml", "icecatProductSyncJob", IcecatFeedKindEnum.PRODUCT), //
	// Files
	iceLang("jobs/languageList.xml", "languageListIcecatJob", IcecatFeedKindEnum.REF_FILE_URL), //
	iceMeasure("/jobs/measureList.xml", "measuresListIcecatJob", IcecatFeedKindEnum.REF_FILE_URL), //
	iceBrand("/jobs/supplierList.xml", "supplierListIcecatJob", IcecatFeedKindEnum.REF_FILE_URL), //
	iceBrandMapping("/jobs/supplierMapping.xml", "supplierMappingIcecatJob", IcecatFeedKindEnum.MAPPING_FILE_URL), //
	iceCategory("/jobs/categoryList.xml", "categoryListIcecatJob", IcecatFeedKindEnum.REF_FILE_URL), //
	iceFeature("/jobs/featureList.xml", "featureListIcecatJob", IcecatFeedKindEnum.REF_FILE_URL), //
	iceCategoryFeature("/jobs/categoryFeaturesList.xml", "categoryFeaturesListIcecatJob", IcecatFeedKindEnum.REF_FILE_URL), //
	PRODUCT_FAMILIES("/jobs/supplierProductFamiliesList.xml", null, IcecatFeedKindEnum.REF_FILE_URL), //
	// TODO
	FEATURE_VALUES_VOCABULARY("/jobs/featureValuesVocabularyList.xml", null, IcecatFeedKindEnum.REF_FILE_URL), //
	iceRelation("/jobs/relationsList.xml", null, IcecatFeedKindEnum.REF_FILE_URL), //
	iceDistribution("/jobs/distributorList.xml", null, IcecatFeedKindEnum.REF_FILE_URL), //

	// Product
	iceProductDaily("/jobs/icecat-index-file.xml", "indexFilesIcecatJob", IcecatFeedKindEnum.PRODUCT), //
	iceProductFull("/jobs/icecat-index-file-diff-es.xml", "indexFilesDiffIcecatJob", IcecatFeedKindEnum.PRODUCT), //

	// CampaignsLists
	iceCampaign("/jobs/campaignsList.xml", null, IcecatFeedKindEnum.REF_FILE_URL), //

	// MAPPING
	// Obsolet MAPPING_PRODUCT("product_mapping.xml", null,
	// IcecatFeedKindEnum.MAPPING_FILE_URL), //
	// PIV
	MAPPING_TB("TB_mapping.txt", null, IcecatFeedKindEnum.PIVOT_FILE_URL), //
	MAPPING_TDDE("TDDE_mapping.txt", null, IcecatFeedKindEnum.PIVOT_FILE_URL), //
	MAPPING_TDES("TDES_mapping.txt", null, IcecatFeedKindEnum.PIVOT_FILE_URL), //
	MAPPING_TDIT("TDIT_mapping.txt", null, IcecatFeedKindEnum.PIVOT_FILE_URL), //
	MAPPING_TD("TD_mapping.txt", null, IcecatFeedKindEnum.PIVOT_FILE_URL);

	private final String jobPath;

	private final String jobIdentifier;

	private final IcecatFeedKindEnum fileDirectoryName;

	IcecatFeedEnum(String fileName, String jobIdentifier, IcecatFeedKindEnum fileDirectoryName) {
		this.jobPath = fileName;
		this.jobIdentifier = jobIdentifier;
		this.fileDirectoryName = fileDirectoryName;
	}

	public String getJobPath() {
		return jobPath;
	}

	public String getJobIdentifier() {
		return jobIdentifier;
	}

	public IcecatFeedKindEnum getFileDirectoryName() {
		return fileDirectoryName;
	}

	public String getRelativeFilePath() {
		return getFileDirectoryName() + getJobPath();
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

	public static List<IcecatFeedEnum> getValidEnums() {
		List<IcecatFeedEnum> results = new ArrayList<IcecatFeedEnum>();
		for (IcecatFeedEnum val : IcecatFeedEnum.values()) {
			if (val.isValidService()) {
				results.add(val);
			}
		}
		return results;
	}

	public static List<IcecatFeedEnum> getReferentialEnums() {
		return getByKindEnums(IcecatFeedKindEnum.REF_FILE_URL);
	}

	public static List<IcecatFeedEnum> getInitEnums() {
		return getByKindEnums(IcecatFeedKindEnum.INIT);
	}

	public static List<IcecatFeedEnum> getByKindEnums(IcecatFeedKindEnum kind) {
		List<IcecatFeedEnum> results = new ArrayList<IcecatFeedEnum>();
		for (IcecatFeedEnum val : IcecatFeedEnum.values()) {
			if (val.isValidService()) {
				if (kind.equals(val.getFileDirectoryName())) {
					results.add(val);
				}
			}
		}
		return results;
	}

}
