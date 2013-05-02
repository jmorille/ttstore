package eu.ttbox.service.search.elasticsearch.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ElasticInitializerMapping {

	String pathRoot() default "";

	String pathMapping() default "";
	
	String indexName() default "";

	String indexType() default "";
	
}
