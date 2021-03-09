package com.midterm.emp.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

    @Enumerated
    private Level level;

    private LocalDate birthday;

    @Column(precision=10, scale=2)
	private BigDecimal baseSalary;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Address> addresses;

	@OneToOne(mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
	private User user;
}
