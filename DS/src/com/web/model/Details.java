package com.web.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Details {

	String author();
	String date();
	int version() default 1;
	String lastModifiedBy() default "N/A";
	String[] reviewers();
	
}
