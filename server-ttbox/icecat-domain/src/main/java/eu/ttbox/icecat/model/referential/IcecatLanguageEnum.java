package eu.ttbox.icecat.model.referential;

import java.io.Serializable;

/**
 * 
 * select SHORT_CODE ||'("' || SHORT_CODE || '",' || LANGID || ',' || SID ||
 * ',"' || CODE || '"),//' from LANGUAGE order by LANGID;
 * 
 * et ajouter en tete :
 * NO_LANG("NO_LANG",0,,"No language, undefined, must not appear in datas"),//
 * 
 */
public enum IcecatLanguageEnum implements Serializable {

	NO_LANG("NO_LANG", 0, "No language, undefined, must not appear in datas"), //
	EN("EN", 1, "english"), //
	NL("NL", 2, "dutch"), //
	FR("FR", 3, "french"), //
	DE("DE", 4, "german"), //
	IT("IT", 5, "italian"), //
	ES("ES", 6, "spanish"), //
	DK("DK", 7, "danish"), //
	RU("RU", 8, "russian"), //
	US("US", 9, "us english"), //
	BR("BR", 10, "brazilian-portuguese"), //
	PT("PT", 11, "portuguese"), //
	ZH("ZH", 12, "chinese"), //
	SE("SE", 13, "swedish"), //
	PL("PL", 14, "polish"), //
	CZ("CZ", 15, "czech"), //
	HU("HU", 16, "hungarian"), //
	FI("FI", 17, "finnish"), //
	EL("EL", 18, "greek"), //
	NO("NO", 19, "norwegian"), //
	TR("TR", 20, "turkish"), //
	BG("BG", 21, "bulgarian"), //
	KA("KA", 22, "georgian"), //
	RO("RO", 23, "romanian"), //
	SR("SR", 24, "serbian"), //
	UK("UK", 25, "ukrainian"), //
	JA("JA", 26, "japanese"), //
	CA("CA", 27, "catalan"), //
	ES_AR("ES_AR", 28, "argentinian-spanish"), //
	HR("HR", 29, "croatian"), //
	AR("AR", 30, "arabic"), //
	VI("VI", 31, "vietnamese"), //
	KO("KO", 32, "korean"), //
	MK("MK", 33, "macedonian"), //
	SL("SL", 34, "slovenian"), //
	EN_SG("EN_SG", 35, "singapore-english"), //
	EN_ZA("EN_ZA", 36, "south africa-english"), //
	ZH_TW("ZH_TW", 37, "traditional chinese"), //
	HE("HE", 38, "hebrew"), //
	LT("LT", 39, "lithuanian"),//
	LV("LV", 40, "latvian"),//
	EN_IN("EN_IN", 41, "indian-english");//
 
	private final static int ENUM_SIZE = IcecatLanguageEnum.values().length;

	public static IcecatLanguageEnum getByLangId(int wantedLangId) {
		if (wantedLangId < ENUM_SIZE) {
			return IcecatLanguageEnum.values()[wantedLangId];
		} else {
			return null;
		} 
	}

	private final String short_code;

	private final int langid;

	private final String code;

	private IcecatLanguageEnum(String short_code, int langid, String code) {
		this.short_code = short_code;
		this.langid = langid;
		this.code = code;
	}

	public String getShort_code() {
		return short_code;
	}

	public int getLangid() {
		return langid;
	}

	public String getCode() {
		return code;
	}

}
