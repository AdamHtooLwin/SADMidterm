package com.midterm.emp.services;

import javax.money.MonetaryAmount;

import com.midterm.emp.models.Level;

public interface EmployeeService {
    MonetaryAmount calculateNetSalary(Level level, MonetaryAmount baseSalary);
}
