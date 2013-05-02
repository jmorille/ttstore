package eu.ttbox.service.search.elasticsearch.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.admin.indices.optimize.OptimizeResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.template.delete.DeleteIndexTemplateResponse;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.action.admin.cluster.state.ClusterStateRequestBuilder;
import org.elasticsearch.client.action.admin.indices.alias.IndicesAliasesRequestBuilder;
import org.elasticsearch.client.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.client.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.client.action.admin.indices.optimize.OptimizeRequestBuilder;
import org.elasticsearch.client.action.admin.indices.template.put.PutIndexTemplateRequestBuilder;
import org.elasticsearch.client.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.action.index.IndexRequestBuilder;
import org.elasticsearch.cluster.metadata.AliasAction;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.base.Strings;
import org.elasticsearch.common.collect.ImmutableMap;
import org.elasticsearch.common.io.Streams;
import org.elasticsearch.indices.IndexMissingException;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
public class ElasticInitializer implements MethodRule {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	Client client;

	ElasticInitializerDataJsonArrayParser dataParser = new ElasticInitializerDataJsonArrayParser();

	public ElasticInitializer() {
		super();
	}

	public ElasticInitializer(Client elasticClient) {
		super();
		this.client = elasticClient;
	}

	private void manageCleaner(ElasticInitializerClean cleaner) {
		if (cleaner.indexName() != null && cleaner.indexName().length > 0) {
			deleteIndex(cleaner.indexName());
		}
//		if (cleaner.aliases() != null && cleaner.aliases().length > 0) {
//			client.admin().indices().
//		}
	}

	@Override
	public Statement apply(final Statement base, FrameworkMethod frameworkMethod, final Object target) {

		// Read Setting
		final ElasticInitializerSetting config = frameworkMethod.getAnnotation(ElasticInitializerSetting.class);
		final ElasticInitializerClean cleaner = frameworkMethod.getAnnotation(ElasticInitializerClean.class);
		// Read Config
		final List<ElasticInitializerMapping> configMappings = extractConfigMappings(frameworkMethod);
		final List<ElasticInitializerData> dataToLoads = dataToLoads(frameworkMethod);
		final List<ElasticInitializerTemplate> configTemplates = extractConfigTemplate(frameworkMethod);

		// Run Tests
		final boolean isValidEs = config != null;
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				try {
					if (isValidEs) {
						if (cleaner!=null && cleaner.deleteBefore()) {
							manageCleaner(cleaner);
						}
						before(config, configTemplates, configMappings, dataToLoads, target);
					}
					base.evaluate();
				} finally {
					if (isValidEs) {
						after(config, configTemplates);
						if (cleaner!=null && cleaner.deleteAfter()) {
							manageCleaner(cleaner);
						}

					}
				}
			}
		};
	}

	private List<ElasticInitializerMapping> extractConfigMappings(FrameworkMethod frameworkMethod) {
		List<ElasticInitializerMapping> configMappings = new ArrayList<ElasticInitializerMapping>();
		ElasticInitializerMapping configMappingOne = frameworkMethod.getAnnotation(ElasticInitializerMapping.class);
		if (configMappingOne != null) {
			configMappings.add(configMappingOne);
		}
		ElasticInitializerMappings configMappingLists = frameworkMethod.getAnnotation(ElasticInitializerMappings.class);
		if (configMappingLists != null) {
			for (ElasticInitializerMapping aData : configMappingLists.value()) {
				configMappings.add(aData);
			}
		}
		return configMappings;
	}

	private List<ElasticInitializerData> dataToLoads(FrameworkMethod frameworkMethod) {
		List<ElasticInitializerData> dataToLoads = new ArrayList<ElasticInitializerData>();
		ElasticInitializerData dataOne = frameworkMethod.getAnnotation(ElasticInitializerData.class);
		if (dataOne != null) {
			dataToLoads.add(dataOne);
		}
		ElasticInitializerDatas dataLists = frameworkMethod.getAnnotation(ElasticInitializerDatas.class);
		if (dataLists != null) {
			for (ElasticInitializerData aData : dataLists.value()) {
				dataToLoads.add(aData);
			}
		}
		return dataToLoads;
	}

	private List<ElasticInitializerTemplate> extractConfigTemplate(FrameworkMethod frameworkMethod) {
		List<ElasticInitializerTemplate> configTemplates = new ArrayList<ElasticInitializerTemplate>();
		ElasticInitializerTemplate dataOne = frameworkMethod.getAnnotation(ElasticInitializerTemplate.class);
		if (dataOne != null) {
			configTemplates.add(dataOne);
		}
		return configTemplates;
	}

	private String getPathConcatPath(String root, String name) {
		final String PATH_SEPARATOR = "/";
		StringBuffer sb = new StringBuffer(root.length() + name.length() + 1);
		if (!Strings.isNullOrEmpty(root) && !name.startsWith(PATH_SEPARATOR)) {
			sb.append(root);
		}
		if (!Strings.isNullOrEmpty(root) && !root.endsWith(PATH_SEPARATOR) && !name.startsWith(PATH_SEPARATOR)) {
			sb.append(PATH_SEPARATOR);
		}
		sb.append(name);
		return sb.toString();
	}

	private void deleteTemplates(List<ElasticInitializerTemplate> configTemplates) {
		if (configTemplates != null && !configTemplates.isEmpty()) {
			for (ElasticInitializerTemplate template : configTemplates) {
				deleteTemplate(template.templateName());
			}
		}
	}

	private void manageCreateAlias(ElasticInitializerSetting config, ElasticInitializerAlias[] aliases) {
		if (aliases != null && aliases.length > 0) {
			IndicesAliasesRequestBuilder aliasesRequestBuilder = client.admin().indices().prepareAliases();
			for (ElasticInitializerAlias alias : aliases) {
				String indexName = Strings.isNullOrEmpty(alias.indexName()) ? config.indexName() : alias.indexName();
				aliasesRequestBuilder.addAliasAction(new AliasAction(alias.action(), indexName, alias.value()));
			}
			IndicesAliasesResponse response = aliasesRequestBuilder.execute().actionGet();
			Assert.assertTrue(response.acknowledged());
		}
	}

	private void before(ElasticInitializerSetting config, List<ElasticInitializerTemplate> configTemplates, List<ElasticInitializerMapping> configMappings,
			List<ElasticInitializerData> dataLoads, Object target) throws Throwable {
		StopWatch stopWatch = new StopWatch("ElasticInitializer Before");

		if (config.deleteBefore()) {
			stopWatch.start("Delete Index : " + config.indexName());
			deleteIndex(config.indexName());
			deleteTemplates(configTemplates);
			stopWatch.stop();
		}
		// Create mapping
		// Templates
		if (configTemplates != null && !configTemplates.isEmpty()) {
			stopWatch.start("Create Template");
			for (ElasticInitializerTemplate template : configTemplates) {
				manageConfigTemplate(template, config, target);
			}
			stopWatch.stop();
		}
		if (true) {
			stopWatch.start("Create Setting");
			String setting = getPathSettingContentString(config, target);
			createSetting(config.indexName(), setting);
			manageCreateAlias(config, config.aliases());
			stopWatch.stop();

		}
		// Mappings
		if (configMappings != null && !configMappings.isEmpty()) {
			stopWatch.start("Create Mapping");
			for (ElasticInitializerMapping configMapping : configMappings) {
				if (configMapping != null) {
					String indexName = Strings.isNullOrEmpty(configMapping.indexName()) ? config.indexName() : configMapping.indexName();
					String indexType = Strings.isNullOrEmpty(configMapping.indexType()) ? config.indexType() : configMapping.indexType();
					String mappingContent = getPathMappingContentString(configMapping, config, target);
					createMapping(indexName, indexType, mappingContent);
				}
			}
			stopWatch.stop();
		}
		// Load Datas
		if (dataLoads != null && !dataLoads.isEmpty()) {
			stopWatch.start("Load Datas");
			for (ElasticInitializerData dataLoad : dataLoads) {
				manageLoadData(dataLoad, config, target);
			}
			stopWatch.stop();
			// Flush
			stopWatch.start("Flush Datas");
			refresh(config.indexName());
			optimize(config.indexName());
			stopWatch.stop();
		}
		if (log.isDebugEnabled()) {
			log.debug(stopWatch.prettyPrint());
		}
	}

	private String getPathMappingContentString(ElasticInitializerMapping configMapping, ElasticInitializerSetting config, Object target) {
		String mappingPath = getPathConcatPath(configMapping.pathRoot(), configMapping.pathMapping());
		String mapping = copyToStringFromClasspath(target, mappingPath, config.encoding());
		return mapping;
	}

	private String getPathSettingContentString(ElasticInitializerSetting config, Object target) {
		String pathSetting = getPathConcatPath(config.pathRoot(), config.pathSetting());
		String setting = copyToStringFromClasspath(target, pathSetting, config.encoding());
		return setting;
	}

	public void deleteTemplate(String templateName) {
		try {
			DeleteIndexTemplateResponse response = client.admin().indices().prepareDeleteTemplate(templateName).execute().actionGet();
			Assert.assertTrue(response.acknowledged());
		} catch (Exception e) {
			log.debug("Not found template {} for delete operation", templateName);
		}
	}

	private void manageConfigTemplate(ElasticInitializerTemplate template, ElasticInitializerSetting config, Object target) throws IOException {
		String templateName = template.templateName();
		PutIndexTemplateRequestBuilder templateBuilder = client.admin().indices().preparePutTemplate(templateName).setTemplate(template.templatePattern());
		// Template Setting
		if (!Strings.isNullOrEmpty(template.pathSetting())) {
			String pathSetting = getPathConcatPath(template.pathSettingRoot(), template.pathSetting());
			String setting = copyToStringFromClasspath(target, pathSetting, config.encoding());
			templateBuilder.setSettings(setting);
			log.debug("Add Template [{}] setting for pathSetting=[{}]", templateName, pathSetting);
		}
		// Template Mapping
		if (template.mapping() != null && template.mapping().length > 0) {
			for (ElasticInitializerMapping mappingConfig : template.mapping()) {
				String indexType = Strings.isNullOrEmpty(mappingConfig.indexType()) ? config.indexType() : mappingConfig.indexType();
				String mappingContent = getPathMappingContentString(mappingConfig, config, target);
				templateBuilder.addMapping(indexType, mappingContent);
				log.debug("Add Template [{}] mapping in indexType=[{}]", templateName, indexType);
			}
		}
		// Order
		templateBuilder.setOrder(template.order());

		// Send Template
		PutIndexTemplateResponse response = templateBuilder.execute().actionGet();
		Assert.assertTrue(response.acknowledged());
	}

	private void manageLoadData(ElasticInitializerData dataLoad, ElasticInitializerSetting config, Object target) throws Exception {
		if (dataLoad != null && dataLoad.fileDatas().length > 0) {
			// Convert File To Data to load
			String indexName = config.indexName();
			String indexType = Strings.isNullOrEmpty(dataLoad.indexType()) ? config.indexType() : dataLoad.indexType();
			String rootData = dataLoad.pathRoot();
			for (String data : dataLoad.fileDatas()) {
				String dataPath = getPathConcatPath(rootData, data);
				long begin = System.currentTimeMillis();
				byte[] datas = copyToByteArrayFromClasspath(target, dataPath);
				// Load Datas
				loadDatas(indexName, indexType, datas);
				long end = System.currentTimeMillis();
				log.debug("Load data {} to index {}/{} in {} ms.", new Object[] { dataPath, indexName, indexType, (end - begin) });
			}
		}
	}

	private void after(ElasticInitializerSetting config, List<ElasticInitializerTemplate> configTemplates) throws Exception {
		if (config.deleteAfter()) {
			deleteIndex(config.indexName());
			deleteTemplates(configTemplates);
		}
	}

	public void deleteIndex(String... indexName)   {
		try {
			DeleteIndexResponse response =  client.admin().indices().prepareDelete(indexName).execute().actionGet();
			log.debug("Delete Index {} : {}", indexName, response.acknowledged());
		} catch (IndexMissingException e) {
			log.debug("Delete Index {} : false ( {} )", indexName, e.getMessage());
		}
	}

	public void createSetting(String indexName, String settingContent) throws Exception {
		log.debug("Create setting from {}", indexName);
		// log.info(source);
		CreateIndexRequestBuilder createIndx = client.admin().indices().prepareCreate(indexName);
		if (!Strings.isNullOrEmpty(settingContent)) {
			createIndx.setSettings(settingContent);
		}
		CreateIndexResponse response = createIndx.execute().actionGet();
		Assert.assertTrue(response.acknowledged());
	}

	public void createMapping(String indexName, String indexType, String mappingContent) throws Exception {
		if (mappingContent == null || mappingContent.length() < 1) {
			return;
		}
		// Create Request
		PutMappingRequestBuilder request = client.admin().indices().preparePutMapping(indexName);
		request.setSource(mappingContent).setType(indexType);

		PutMappingResponse response = request.execute().actionGet();
		log.debug("Create Mapping for {} / {} ", indexName, indexType);
		Assert.assertTrue(response.acknowledged());
	}

	public void loadDatas(String indexName, String indexType, byte[] datas) throws Exception {
		// System.out.println("data : " + datas);
		// Prepare Bulk
		BulkRequestBuilder bulkRequest = client.prepareBulk().setRefresh(true);

		List<String> jsonEntities = dataParser.convertAsList(datas);
		for (String jsonEntry : jsonEntities) {
			IndexRequestBuilder indexRequestData = client.prepareIndex(indexName, indexType);
			indexRequestData.setSource(jsonEntry);
			bulkRequest.add(indexRequestData);
		}

		// Execute bulk
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			// process failures by iterating through each bulk response item
			log.error(bulkResponse.buildFailureMessage());
		} else {
			log.debug("Elasticsearch Add data in Index {} succefully in {} ms.", indexName, bulkResponse.tookInMillis());
		}
	}

	public void optimize(String indexName) throws Exception {
		OptimizeRequestBuilder optimizeRequest = client.admin().indices().prepareOptimize(indexName).setMaxNumSegments(1);
		OptimizeResponse optimizeRes = optimizeRequest.execute().actionGet();
		Assert.assertTrue(optimizeRes.failedShards() < 1);
	}

	public void refresh(String indexName) throws Exception {
		client.admin().indices().refresh(new RefreshRequest(indexName)).actionGet();
	}

	public void consultSetting() throws Exception {
		ClusterStateRequestBuilder request = client.admin().cluster().prepareState();
		ClusterStateResponse response = request.execute().actionGet();

		// BytesStreamOutput out = new BytesStreamOutput();
		// response.writeTo(out);
		// System.out.println("**************************************");
		// System.out.println(new String(out.copiedByteArray()));
		// System.out.println("**************************************");

		ImmutableMap<String, IndexMetaData> map = response.state().metaData().indices();
		for (String key : map.keySet()) {
			IndexMetaData val = map.get(key);
			ImmutableMap<String, MappingMetaData> mappings = val.mappings();
			for (String mappingKey : mappings.keySet()) {
				MappingMetaData mapping = mappings.get(mappingKey);
				System.out.println(mapping.source());
			}
		}
		// getRequest.setIndex("_mapping");
		// GetResponse response = getRequest.execute().actionGet();
		// System.out.println("---------------------------------------------");
		// System.out.println( response.sourceAsString());
		// System.out.println("---------------------------------------------");
	}

	public String copyToStringFromClasspath(Object target, String path, String encoding) {
		String fileEncoding = encoding;
		String result = null;
		if (Strings.isNullOrEmpty(fileEncoding)) {
			fileEncoding = "UTF-8";
		}
		if (!Strings.isNullOrEmpty(path)) {
			InputStream is = target.getClass().getResourceAsStream(path);
			try {
				result = Streams.copyToString(new InputStreamReader(is, fileEncoding));
				is.close();
			} catch (Exception e) {
				throw new RuntimeException("Could not read " + path + "(" + fileEncoding + ")" + " : " + e.getMessage(), e);
			}
		}
		return result;
	}

	public byte[] copyToByteArrayFromClasspath(Object target, String path) throws IOException {
		byte[] datas = null;
		InputStream is = target.getClass().getResourceAsStream(path);
		if (is == null) {
			throw new FileNotFoundException("Resource [" + path + "] not found in classpath");
		}
		try {
			datas = Streams.copyToByteArray(is);
		} finally {
			is.close();
		}
		return datas;
	}

}
