package com.occw.occwinternetbanking.jpa.components;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class Authority {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="customer_id")
	private Customer customer;


	public Long getId() {
		return id;
	}
	
	public String name;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
