package com.onndoo.myapp.custom;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10}");
	private static final Pattern DIGITS = Pattern.compile("[0-9]{10}");

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		// Check if the phone number is valid
		if (phone == null || phone.isEmpty()) {
			return true;  //Let the @NotNull/@NotEmpty annotation handle null or empty values
		}
		return phone != null && phone.matches("\\d{10}");
	}
	
	public static boolean isValidPhoneNumber(String phone) {
		// Check if the phone number is valid
		return DIGITS.matcher(phone).matches();
	}

}
