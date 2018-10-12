package com.khoiphuc27.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.khoiphuc27.dto.CustomerDTO;
import com.khoiphuc27.model.Customer;

public class CustomerFormValidator implements Validator {
	//Which objects can be validated by this validator
	
//	private String PHONE_PATTERN
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return CustomerDTO.class.equals(paramClass);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");
	}
}
