package eu.ttbox.icecat.model.referential;

public enum ClassEnum {
	
	KEY("Key feature"), //
	
	EXTRA("Extra feature - tech details Possible");
	
	private final static int ENUM_SIZE = ClassEnum.values().length;
	
	public static ClassEnum getById(int wantedId) {
		if (wantedId < ENUM_SIZE) {
			return ClassEnum.values()[wantedId];
		} else {
			return null;
		}
	}
	
	private final String description;
	
	ClassEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	

	
}
