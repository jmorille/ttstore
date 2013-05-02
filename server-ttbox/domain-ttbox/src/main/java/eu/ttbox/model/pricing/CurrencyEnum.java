package eu.ttbox.model.pricing;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import eu.ttbox.model.MessageKeyProvider;

/**
 * Currency Code.
 * 
 * @see http://www.xe.com/symbols.php
 * @author deostem
 * 
 */
public enum CurrencyEnum implements MessageKeyProvider, Serializable {
	
	
	EUR; 

	private final String messageKey;

	static Map<String, CurrencyEnum> copyFields = new HashMap<String, CurrencyEnum>();

	static {
		for (CurrencyEnum vo : CurrencyEnum.values()) {
			copyFields.put(vo.name(), vo);
		}
	}
	
	CurrencyEnum() {
		this.messageKey = String.format( "SupplierEnum.%s", this.name());
	}
	
	public String getName() {
		return this.name();
	}

	@Override
	public String getMessageKey() {
		return messageKey;
	}
	
	public static CurrencyEnum getMapping(String name) {
	    return copyFields.get(name);
	}
	
	
}
