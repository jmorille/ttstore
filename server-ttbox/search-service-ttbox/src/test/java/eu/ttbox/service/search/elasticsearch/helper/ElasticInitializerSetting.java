package eu.ttbox.service.search.elasticsearch.helper;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ElasticInitializerSetting  {

	String pathRoot() default "";

	String pathSetting() default "";

//	String settingContent() default "";

	String encoding() default "UTF-8";

	String indexName() default "test";

	String indexType() default "test";

	boolean deleteAfter() default true;
	
	boolean deleteBefore() default true;

	ElasticInitializerAlias[] aliases() default {};
	
}
