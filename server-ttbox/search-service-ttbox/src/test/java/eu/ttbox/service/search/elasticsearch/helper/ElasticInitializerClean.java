package eu.ttbox.service.search.elasticsearch.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ElasticInitializerClean {

	String[] indexName() default {};
 
	boolean deleteAfter() default true;
	
	boolean deleteBefore() default true;
	
}
