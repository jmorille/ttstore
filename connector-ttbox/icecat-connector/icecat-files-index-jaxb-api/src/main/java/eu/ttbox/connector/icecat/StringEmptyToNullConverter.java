package eu.ttbox.connector.icecat;

import javax.xml.bind.DatatypeConverter;

public class StringEmptyToNullConverter {

	/**
	 * <p>
	 * Convert the lexical XSD string argument into a String value.
	 * 
	 * @param lexicalXSDString
	 *            A string containing a lexical representation of xsd:string.
	 * @return A String value represented by the string argument.
	 */
	public static String parseString(String lexicalXSDString) {
		String result = DatatypeConverter.parseString(lexicalXSDString);
		return emptyToNull(result);
	}

	/**
	 * <p>
	 * Converts the string argument into a string.
	 * 
	 * @param val
	 *            A string value.
	 * @return A string containing a lexical representation of xsd:string.
	 */ 
	public static String printString(String val) {
		String result = DatatypeConverter.printString(val);
		return emptyToNull(result);
	}

	public static String emptyToNull(String string) {
		return isNullOrEmpty(string) ? null : string;
	}

	/**
	 * Returns {@code true} if the given string is null or is the empty string.
	 * 
	 * <p>
	 * Consider normalizing your string references with {@link #nullToEmpty}. If
	 * you do, you can use {@link String#isEmpty()} instead of this method, and
	 * you won't need special null-safe forms of methods like
	 * {@link String#toUpperCase} either. Or, if you'd like to normalize "in the
	 * other direction," converting empty strings to {@code null}, you can use
	 * {@link #emptyToNull}.
	 * 
	 * @param string
	 *            a string reference to check
	 * @return {@code true} if the string is null or is the empty string
	 */
	public static boolean isNullOrEmpty(String string) {
		return string == null || string.length() == 0; // string.isEmpty() in
														// Java 6
	}
}
