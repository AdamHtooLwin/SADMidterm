package com.midterm.emp.services;

import com.midterm.emp.models.Address;

public interface AddressService {
    void updateAddress(Address address, String houseNo, String street, String city, String zipCode);
}
