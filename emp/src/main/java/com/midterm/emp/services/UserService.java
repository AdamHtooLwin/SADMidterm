package com.midterm.emp.services;

import com.midterm.emp.models.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);    
}
