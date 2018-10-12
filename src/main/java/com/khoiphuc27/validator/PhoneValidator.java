package com.khoiphuc27.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone paramA) {
	}

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext ctx) {
		if(phone == null || phone.equals("")) {
			return false;
		}
		if (phone.substring(0,1).equals("0")) {
			if (phone.length() == 10)
				return true;
			else
				return false;
		}
		else if (phone.substring(0,1).equals("+")) {
			if (phone.length() == 12)
				return true;
			else
				return false;
		}
		else
			return false;
	}
}
