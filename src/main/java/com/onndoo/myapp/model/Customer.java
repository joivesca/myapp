package com.onndoo.myapp.model;

import com.onndoo.myapp.custom.Phone;

import jakarta.validation.constraints.NotNull;

public class Customer {

	@Phone
	@NotNull(message = "{customer.phone.notnull}")
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
}
