package com.midterm.emp.services;

import javax.money.MonetaryAmount;

import com.midterm.emp.models.Level;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Override
    public MonetaryAmount calculateNetSalary(Level level, MonetaryAmount baseSalary) {
        // TODO Auto-generated method stub
        MonetaryAmount positionSalary = level.getSalary(level);

        MonetaryAmount netSalary = baseSalary.add(positionSalary);

        return netSalary;
    }
    
}
