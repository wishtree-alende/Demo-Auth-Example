package com.bezkoder.springjwt.security.services;

import java.util.List;

import com.bezkoder.springjwt.models.User;

public interface UserFuncService {

	List<User> getUserList();
	
	void deleteUser(Long id);
}
