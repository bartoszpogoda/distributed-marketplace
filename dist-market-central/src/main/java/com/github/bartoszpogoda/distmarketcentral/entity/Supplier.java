package com.github.bartoszpogoda.distmarketcentral.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
	
	@Id	
	private String id;
	
	private String name;

	private String apiKey;

	private String createOrderHook;

	private boolean active;

}
