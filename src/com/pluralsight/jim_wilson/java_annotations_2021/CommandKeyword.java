package com.pluralsight.jim_wilson.java_annotations_2021;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandKeyword {
	// declare an element called value of type String
	String value();

	// declare a default-valued element
	String method() default "calculate";
}
