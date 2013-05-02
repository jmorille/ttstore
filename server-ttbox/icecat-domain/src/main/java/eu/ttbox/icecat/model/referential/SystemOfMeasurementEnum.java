package eu.ttbox.icecat.model.referential;

public enum SystemOfMeasurementEnum {
	metric, imperial; 
	
	private final static int ENUM_SIZE = SystemOfMeasurementEnum.values().length;
	
	public static SystemOfMeasurementEnum getById(int wantedId) {
		if (wantedId < ENUM_SIZE) {
			return SystemOfMeasurementEnum.values()[wantedId];
		} else {
			return null;
		}
	}
}
