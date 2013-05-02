package eu.ttbox.service.search.elasticsearch.helper;

import org.elasticsearch.cluster.metadata.AliasAction;

public @interface ElasticInitializerAlias {

	String indexName() default "";
	
	String value() default "";
	
	AliasAction.Type action() default AliasAction.Type.ADD;
	
}
