package br.com.zupacademy.rodrigo.proposta.validation;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class Base64ValueValidator implements ConstraintValidator<Base64Value, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			Base64.getDecoder().decode(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
