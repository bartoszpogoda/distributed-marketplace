package com.github.bartoszpogoda.distmarketcentral.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Supplier {
	
	@Id	
	private String id;
	
	private String name;

}
