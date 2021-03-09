package com.midterm.emp.services;

import com.midterm.emp.dao.UserJPADao;
import com.midterm.emp.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserJPADao userdao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public void save(User user) {
		System.out.println("here");
		String hashedPassword = bcryptEncoder
				.encode(user.getPassword());
		user.setPassword(hashedPassword);
		user.setActive(true);
		userdao.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userdao.findByUsername(username);
	}

}
