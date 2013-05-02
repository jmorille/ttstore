package eu.ttbox.icecat.model.referential;

/**
 * 
 * Each searchable feature will have LimitDirection attribute defined, which
 * will give an advice for finding a "better" feature value, either or . E.g.
 * for feature "Hard disk capacity" it would be >= relation, and for the feature
 * "Write seek" it would be <= relation.
 * 
 * @author jmorille
 * 
 */
public enum LimitDirectionEnum {
	undefined("The relation is undefined"), // ,
	inf("The relation is =<"), //
	sup("The relation is >="), //
	EQUAL("The relation is =");

	private final static int ENUM_SIZE = LimitDirectionEnum.values().length;
	
	public static LimitDirectionEnum getById(int wantedId) {
		if (wantedId < ENUM_SIZE) {
			return LimitDirectionEnum.values()[wantedId];
		} else {
			return null;
		}
	}
	
	
	private final String description;
 
	LimitDirectionEnum(String description) {
		this.description = description;
	}


	public String getDescription() {
		return description;
	}
	
	
	
}
