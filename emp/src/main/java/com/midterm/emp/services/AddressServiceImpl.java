package com.midterm.emp.services;

import com.midterm.emp.dao.AddressJPADao;
import com.midterm.emp.models.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressJPADao aDao;

    @Override
    public void updateAddress(Address address, String houseNo, String street, String city, String zipCode) {
        // there has to be a better way!!!!!1
        
        if (!houseNo.isEmpty()) {
            address.setHouseNo(houseNo);
        }

        if (!street.isEmpty()) {
            address.setStreet(street);
        }

        if (!city.isEmpty()) {
            address.setCity(city);
        }

        if (!zipCode.isEmpty()) {
            address.setZipCode(zipCode);
        }
        
        aDao.save(address);
    }
    
}
