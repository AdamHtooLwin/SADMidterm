package com.midterm.emp.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    private String city;

    private String street;

    private String houseNo;

    private String zipCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee emp;
}
