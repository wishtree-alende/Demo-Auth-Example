package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.UserFuncService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserFuncService userService;

	@GetMapping("/getUserData")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUserList() {
		List<User> users = userService.getUserList();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUserData/{id}")
	public void deleteUserData(@PathVariable Long id) {
		userService.deleteUser(id);
	}

}
