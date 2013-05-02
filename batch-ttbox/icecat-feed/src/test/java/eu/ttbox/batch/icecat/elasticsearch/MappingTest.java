package eu.ttbox.batch.icecat.elasticsearch;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappingTest {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void testMappingJson() throws IOException {
		// XContentBuilder content = XContentFactory.smileBuilder().startObject();
		XContentBuilder content = XContentFactory.jsonBuilder().startObject();
		content.field("nom", "Jerome");
		content.endObject();

		log.info(content.string());
	}

	private Jack getJackModel() {
		Jack jack = new Jack();
		jack.setFirstname("Daniel");
		jack.setLastname("jack");
		jack.setOptional("Optional value");
		Daniel daniel = new Daniel("1,5l", "10 ans", null);
		jack.setDaniel(daniel);
		return jack;
	}

	@Test
	public void testMappingModelJsonModel() throws IOException {
		Jack jack = getJackModel();
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
		// mapper.configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS, true);
		// mapper.configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, false);
		// mapper.configure(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS, false);
		// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, false);

		// StringWriter sw = new StringWriter();
		mapper.writeValue(System.out, jack);
	}

	@Test
	public void testMappingJsonToModel() throws IOException {
		Jack jack = getJackModel();
		System.out.println("Model Init : " + jack);
		ObjectMapper mapper = new ObjectMapper();
		// Model as Json
		StringWriter sb = new StringWriter(512);
		mapper.writeValue(sb, jack);
		String jackAsJson = sb.getBuffer().toString();
		System.out.println("Model : " + jackAsJson);
		// Json as Model
		Jack jackRead = mapper.readValue(jackAsJson.getBytes(), Jack.class);
		Assert.assertEquals(jack, jackRead);
		System.out.println("Model Read : " + jackRead);
	}
}
