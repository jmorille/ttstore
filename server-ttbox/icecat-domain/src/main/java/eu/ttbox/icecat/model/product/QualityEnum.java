package eu.ttbox.icecat.model.product;

public enum QualityEnum {
	REMOVED("To Delete Product"), //

	LIGHT("Light Product description When not pre-exisitng product"),

	SUPPLIER(
			"The content is received from a supplier CMS, but not standardized by an ICEcat editor. The language-specific directories are likely to contain the full (not standardized) data-sheet."), //

	ICECAT(
			"The content is entered or standardized by ICECAT editors. The standardized data can be found in the INT directory and the language-specific directories."), //

	NOEDITOR(
			"The content is received from a merchant (in most cases one of the 100s of distributors we are daily 'polling') and may be parsed. Editors haven't described this product yet. The NOEDITOR data is not exported in XML to 3rd parties.");

	private final static int ENUM_SIZE = QualityEnum.values().length;

	public static QualityEnum getById(int wantedId) {
		if (wantedId < ENUM_SIZE) {
			return QualityEnum.values()[wantedId];
		} else {
			return null;
		}
	}

	private final String description;

	QualityEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
