package eu.ttbox.icecat.model;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.ttbox.icecat.model.referential.IcecatLanguageEnum;
import eu.ttbox.icecat.model.referential.IcecatTex;
import eu.ttbox.icecat.model.referential.IcecatVocabulary;

public class IcecatHelper {

	private static Pattern replaceEndLinePattern = Pattern.compile("\\n");

	public static String getName(Map<IcecatLanguageEnum, IcecatVocabulary> names) {
		if (names==null) {
			return null;
		}
		final IcecatLanguageEnum[] lngs = new IcecatLanguageEnum[] {
				IcecatLanguageEnum.FR, IcecatLanguageEnum.EN };
		for (IcecatLanguageEnum lng : lngs) {
			IcecatVocabulary nameVoc = names.get(lng);
			if (nameVoc != null) {
				String name = nameVoc.getValue();
				if (name != null) {
					return name;
				}
			}
		}
		return null;
	}

	public static Object getByIcecatLanguageEnum(
			Map<IcecatLanguageEnum, ? extends Object> names) {
		if (names==null) {
			return null;
		}
		final IcecatLanguageEnum[] lngs = new IcecatLanguageEnum[] {
				IcecatLanguageEnum.FR, IcecatLanguageEnum.EN };
		for (IcecatLanguageEnum lng : lngs) {
			Object nameVoc = names.get(lng);
			if (nameVoc != null) {
				return nameVoc;
			}
		}
		return null;
	}

	public static String getDescription(Map<IcecatLanguageEnum, IcecatTex> names) {
		if (names==null) {
			return null;
		}
		final IcecatLanguageEnum[] lngs = new IcecatLanguageEnum[] {
				IcecatLanguageEnum.FR, IcecatLanguageEnum.EN };
		for (IcecatLanguageEnum lng : lngs) {
			IcecatTex nameVoc = names.get(lng);
			if (nameVoc != null) {
				String name = nameVoc.getValue();
				if (name != null) {
					return name;
				}
			}
		}
		return null;
	}

	public static String getHtmlFormatedText(String txt) {
		String result = txt;
		if (txt != null) {
			Matcher m = replaceEndLinePattern.matcher(txt);
			result = m.replaceAll("<br/>");
		}
		return result;
	}

}
