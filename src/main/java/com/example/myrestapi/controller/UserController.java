package com.example.myrestapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myrestapi.bean.SwapGenderRequest;
import com.example.myrestapi.entity.User;
import com.example.myrestapi.repository.UserRepository;
import com.example.myrestapi.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		// List<User> users = new ArrayList<>();
		// users.add(user);
		// users.add(user2);
		// System.out.println(users);
		return this.userRepository.findAll();
	}

	@GetMapping("/user/{userId}")
	public User getUserById(@PathVariable("userId") Long userId) {
		User userById = this.userRepository.findByUserId(userId);
		return userById;
	}

	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	@PutMapping("/useractive/{userId}")
	public User updateUserActive(@PathVariable("userId") Long userId) {
		return this.userService.swapActive(userId);
	}

	@PostMapping("/user-swap-gender")
	public User updateSwapGender(@RequestBody SwapGenderRequest req) {
		return this.userService.swapGender(req.getUserId());
	}
	
	@PutMapping("/userverify")
	public User updateUserVerify(@RequestBody User user) {
		Long userId = user.getUserId();
		Boolean verify = user.getVerifyCheck();
		User userById = this.userRepository.findByUserId(userId);
		if (userById != null) {
			userById.setVerifyCheck(verify);
			this.userRepository.save(userById);
		}
		return userById;
	}

	@DeleteMapping("/user/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		System.out.println(userId);
		this.userRepository.deleteById(userId);
	}

	@PutMapping("/user/reset-verify")
	public void resetVerifyChecked() {
		this.userService.resetVerifyCheck();
	}

}
