package com.giantlink.introduction.controllers;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD , ElementType.PARAMETER})

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ImageFileValidator.class})
public @interface ValidImage {

	 String message() default "Invalid image file";

	    Class<?>[] groups() default {};

	    Class<? extends Payload>[] payload() default {};
}
