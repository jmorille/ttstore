package eu.ttbox.model.supplier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import eu.ttbox.model.MessageKeyProvider;


public enum SupplierEnum implements MessageKeyProvider, Serializable {

	// WARNING: Ordinal Use of this Enum in  {@see SupplierPrice.class }
	COMPANY, TECHDATA, INGRAM ;
	
	private final String messageKey;
	
	private static Map<String, SupplierEnum> copyFields = new HashMap<String, SupplierEnum>();

	static {
		for (SupplierEnum vo : SupplierEnum.values()) {
			copyFields.put(vo.name(), vo);
		}
	}
	
	private SupplierEnum() {
		this.messageKey = String.format( "SupplierEnum.%s", this.name()); 
	}
	
	public String getName() {
		return this.name();
	}

	@Override
	public String getMessageKey() {
		return messageKey;
	}
	
	/**
	 * Get the corresponding Mapping.
	 * @param productField The Lucene Field.
	 * @return
	 */
	public static SupplierEnum getMapping(String name) {
	    return copyFields.get(name);
	}
	
}
