package com.midterm.emp.dao;

import com.midterm.emp.models.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
public interface AddressJPADao extends JpaRepository<Address, Integer>{
    
}
