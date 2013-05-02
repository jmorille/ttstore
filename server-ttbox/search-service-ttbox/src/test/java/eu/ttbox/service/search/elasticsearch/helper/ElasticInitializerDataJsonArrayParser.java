package eu.ttbox.service.search.elasticsearch.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ElasticInitializerDataJsonArrayParser {

	private String datasNode = "datas";

	public List<String> convertAsList(String datas) throws JsonParseException, JsonMappingException, IOException {
		List<String> result = new ArrayList<String>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readValue(datas, JsonNode.class);
		for (JsonNode node : rootNode.path(datasNode)) {
			result.add(node.toString());
		}
		return result;
	}

	public List<String> convertAsList(byte[] datas) throws JsonParseException, JsonMappingException, IOException {
		List<String> result = new ArrayList<String>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readValue(datas, JsonNode.class);
		for (JsonNode node : rootNode.path(datasNode)) {
			result.add(node.toString());
		}
		return result;
	}

}
