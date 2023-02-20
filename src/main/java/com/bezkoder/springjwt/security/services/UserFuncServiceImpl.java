package com.bezkoder.springjwt.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;

@Service
public class UserFuncServiceImpl implements UserFuncService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getUserList() {
		List<User> users = userRepo.findAll();
		return users;
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);

	}

}
