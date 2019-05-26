package com.github.bartoszpogoda.distmarketcentral.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SupplierRegistrationForm {

	@NotEmpty
	private String id;

	@NotEmpty
	private String name;

}
