package com.midterm.emp.controllers;

import java.security.Principal;

import com.midterm.emp.dao.AddressJPADao;
import com.midterm.emp.models.Address;
import com.midterm.emp.models.Employee;
import com.midterm.emp.models.User;
import com.midterm.emp.services.AddressService;
import com.midterm.emp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController {
    @Autowired
    AddressJPADao adDao;

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @GetMapping(path = "/address/{id}/delete")
    public String delete(@PathVariable("id") int id, Principal principal) throws Exception {
        Address address = adDao.getOne(id);
        User user = userService.findByUsername(principal.getName());
        Employee employee = user.getEmp();

        if (employee.getAddresses().size() == 1){
            throw new Exception("Must have one address");
        }

        adDao.delete(address);

        return "redirect:/employee/" + user.getEmp().getId();
    }

    @PostMapping(path = "/addresses")
    public String addAddress(Address address, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        address.setEmp(user.getEmp());

        adDao.save(address);
        
        return "redirect:/employee/" + user.getEmp().getId();
    }

    @GetMapping(path = "address/{id}/edit")
    public ModelAndView editAddress(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("/editAddress.jsp");
        Address address = adDao.getOne(id);
        mv.addObject("address", address);

        return mv;
    }

    @PostMapping(path = "address/{id}/edit")
    public String updateAddress(Principal principal, @PathVariable("id") int id,
                                        @RequestParam(name = "houseNo") String houseNo, 
                                        @RequestParam(name = "street") String street,
                                        @RequestParam(name = "city") String city,
                                        @RequestParam(name = "zipCode") String zipCode                         
    ) {
        
        User user = userService.findByUsername(principal.getName());
        Address address = adDao.getOne(id);

        addressService.updateAddress(address, houseNo, street, city, zipCode);

        return "redirect:/employee/" + user.getEmp().getId();
    }
}
