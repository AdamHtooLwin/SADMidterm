package com.midterm.emp.controllers;

import java.security.Principal;
import java.util.List;

import com.midterm.emp.dao.EmployeeJPADao;
import com.midterm.emp.dao.UserJPADao;
import com.midterm.emp.models.Employee;
import com.midterm.emp.models.User;
import com.midterm.emp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeJPADao employeeDao;

    @Autowired
    private UserJPADao userDao;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/")
    public ModelAndView home(Principal principal) {
        ModelAndView mv = new ModelAndView("home.jsp");
        User user = userService.findByUsername(principal.getName());
        mv.addObject("user", user);

        return mv;
    }

    @GetMapping(path = "/admin")
    public ModelAndView admin(Principal principal) {
        ModelAndView mv = new ModelAndView("admin.jsp");
        User user = userService.findByUsername(principal.getName());
        mv.addObject("user", user);

        List<User> users = userDao.findAll();

        // for (Role role: u.getRoles()) {
        //     if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
        //         System.out.println("Current user is " + role.getName());
        //         mv.addObject("admin_flag", true);
        //     }
        // }

        return mv;
    }

    @GetMapping(path = "/employee/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        Employee employee = employeeDao.getOne(id);
        employeeDao.delete(employee);

        return "/admin";
    }
}
