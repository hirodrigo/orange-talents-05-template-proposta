package br.com.zupacademy.rodrigo.proposta.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {Base64ValueValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64Value {

	String message() default "- Esse valor precisa estar em Base64.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}