package eu.ttbox.batch.icecat.product.detail.parser;

public class IcecatToHtmlParserImpl {

	private boolean parseToHtml = true;

	public String parse(String ori) {
		String result = ori;
		if (parseToHtml) {
			result = parseHtml(ori);
		}
		return result;
	}

	private String parseHtml(String ori) {
		String result = ori;

		return result;
	}
}
