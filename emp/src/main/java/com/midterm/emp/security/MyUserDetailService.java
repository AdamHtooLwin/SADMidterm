package com.midterm.emp.security;

import com.midterm.emp.dao.UserJPADao;
import com.midterm.emp.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    // only repo -> can be autowired
    @Autowired
    private UserJPADao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User 404");
        }

        return new UserDetailsImpl(user);
    }
    
    
}
