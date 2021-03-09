package com.midterm.emp.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.money.MonetaryAmount;

import com.midterm.emp.dao.EmployeeJPADao;
import com.midterm.emp.dao.UserJPADao;
import com.midterm.emp.models.Employee;
import com.midterm.emp.models.Level;
import com.midterm.emp.models.User;
import com.midterm.emp.services.AdminService;
import com.midterm.emp.services.EmployeeService;
import com.midterm.emp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService empService;
    
    @Autowired
    private EmployeeJPADao employeeDao;

    @Autowired
    private UserJPADao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

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
        mv.addObject("users", users);

        for (User u: users) {
            Employee emp = u.getEmp();
            MonetaryAmount netSalary = empService.calculateNetSalary(emp.getLevel(), emp.getBaseSalary_());
            emp.setNetSalary(netSalary);
        }

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

    @GetMapping(path = "/users/create")
    public String createUser() {
        return "/createUser.jsp";
    }

    @PostMapping(path = "/users")
    public String createUser(User user,
                                        @RequestParam(name = "name") String name, 
                                        @RequestParam(name = "level") String level,
                                        @RequestParam(name = "baseSalary") String baseSalary,
                                        @RequestParam(name = "birthday") String birthday                          
    ) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(birthday, formatter);

        Employee employee = Employee.builder().name(name)
                                    .level(Level.valueOf(level))
                                    .baseSalary(new BigDecimal(baseSalary))
                                    .birthday(date)
                                .build();
        user.setEmp(employee);
        userService.save(user);

        return "redirect:/admin";
    }

    @GetMapping(path = "employee/{id}/edit")
    public ModelAndView editEmployee(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("/editEmployee.jsp");
        Employee employee = employeeDao.getOne(id);
        mv.addObject("employee", employee);

        return mv;
    }

    @GetMapping(path = "employee/{id}")
    public ModelAndView viewProfile(@PathVariable("id") int id, Principal principal) {
        ModelAndView mv = new ModelAndView("/viewProfile.jsp");
        User user = userService.findByUsername(principal.getName());
        mv.addObject("user", user);

        return mv;
    }

    @PostMapping(path = "employee/{id}/edit")
    public String updateEmployee(@PathVariable("id") int id,
                                        @RequestParam(name = "name") String name, 
                                        @RequestParam(name = "level") String level,
                                        @RequestParam(name = "baseSalary") String baseSalary                          
    ) {
        
        Employee employee = employeeDao.getOne(id);
        
        adminService.adminUpdate(employee, name, level, baseSalary);

        return "redirect:/admin";
    }

    @PostMapping(path = "employee/{id}/editBirthday")
    public String updateEmployeeBirthday(@PathVariable("id") int id,
                                        @RequestParam(name = "birthday") String birthday                        
    ) {
        
        Employee employee = employeeDao.getOne(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(birthday, formatter);
        
        employee.setBirthday(date);
        employeeDao.save(employee);

        return "redirect:/employee/" + employee.getId();
    }
}
