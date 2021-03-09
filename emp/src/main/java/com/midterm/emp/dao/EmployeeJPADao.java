package com.midterm.emp.dao;

import com.midterm.emp.models.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeJPADao extends JpaRepository<Employee, Integer>{
    
}
