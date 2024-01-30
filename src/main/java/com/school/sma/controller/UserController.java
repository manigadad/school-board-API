package com.school.sma.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sma.requestDTO.UserRequest;
import com.school.sma.responseDTO.UserResponse;
import com.school.sma.service.UserService;
import com.school.sma.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController

public class UserController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/users/register")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser( @RequestBody @Valid UserRequest userrequest){
		return userService.saveUser(userrequest);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(@PathVariable int userId) {
		return userService.findUser(userId);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable int userId,@Valid @RequestBody UserRequest userrequest) {
		return userService.updateUser(userId,userrequest);
 
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId) {
	
		return userService.deleteUser(userId);
		
    }
	
	@PostMapping("/users/admin")
	public ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(@PathVariable UserRequest request){
		return userService.registerAdmin(request);
	}
	
	@PostMapping("/users/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<UserResponse>> addOtherUser(@RequestBody UserRequest request){
		return userService.addOtherUser(request);
	}
	
	
	
}
