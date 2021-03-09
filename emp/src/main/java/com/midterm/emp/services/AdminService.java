package com.midterm.emp.services;

import com.midterm.emp.models.Employee;

public interface AdminService {
    void adminUpdate(Employee employee, String name, String level, String baseSalary);
}
