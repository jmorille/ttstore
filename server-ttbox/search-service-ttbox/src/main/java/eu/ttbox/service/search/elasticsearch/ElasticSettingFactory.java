package eu.ttbox.service.search.elasticsearch;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.FactoryBean;

public class ElasticSettingFactory implements FactoryBean<Settings> {

	private String resourceName;

	private Map<String, String> settings;
	
	
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setSettings(Map<String, String> settings) {
		this.settings = settings;
	}

	private Settings immutableSettings;

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		immutableSettings = createSettings();
	}

	public Settings createSettings() throws Exception {
		Builder builder = ImmutableSettings.settingsBuilder();
		if (resourceName != null && resourceName.length() > 0) {
			builder.loadFromClasspath(resourceName);
		}
		if (settings != null && !settings.isEmpty()) {
			builder.put(settings); 
		}
		return builder.build();
	}

	@Override
	public Settings getObject() throws Exception {
		return immutableSettings;
	}

	@Override
	public Class<?> getObjectType() {
		return Settings.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
