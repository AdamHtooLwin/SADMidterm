package com.midterm.emp.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
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
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.midterm.emp.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Transient
    @Autowired
    EmployeeService empService;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @NotBlank(message = "This field is required")
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

    @Transient
	private MonetaryAmount baseSalary_;

    @Transient
	private MonetaryAmount netSalary;

    @PostLoad
	protected void onPostLoad() {
		this.baseSalary_ =
				Monetary.getDefaultAmountFactory()
					.setNumber(this.baseSalary)
					.setCurrency("USD")
					.create();

        // this.netSalary = empService.calculateNetSalary(this.getLevel(), this.baseSalary_);
	}
}
