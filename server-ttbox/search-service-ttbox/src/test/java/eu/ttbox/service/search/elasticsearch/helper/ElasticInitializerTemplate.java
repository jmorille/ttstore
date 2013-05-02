package eu.ttbox.service.search.elasticsearch.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ElasticInitializerTemplate {

	String templateName();

	String templatePattern();

	int order() default 0;

	String pathSettingRoot() default "";

	String pathSetting() default "";

	ElasticInitializerMapping[] mapping() default {};

	 

}
