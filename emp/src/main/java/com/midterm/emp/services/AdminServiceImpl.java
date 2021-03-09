package com.midterm.emp.services;

import java.math.BigDecimal;

import com.midterm.emp.dao.EmployeeJPADao;
import com.midterm.emp.models.Employee;
import com.midterm.emp.models.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private EmployeeJPADao eDao;

    @Override
    public void adminUpdate(Employee employee, String name, String level, String baseSalary) {
        if (!name.isEmpty()) {
            employee.setName(name);
        }

        if (!level.isEmpty()) {
            employee.setLevel(Level.valueOf(level));
        }

        if (!baseSalary.isEmpty()) {
            employee.setBaseSalary(new BigDecimal(baseSalary));
        }
        eDao.save(employee);
        
    }
    
}
