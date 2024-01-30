package com.school.sma.service;


import org.springframework.http.ResponseEntity;

import com.school.sma.enums.UserRole;
import com.school.sma.requestDTO.UserRequest;
import com.school.sma.responseDTO.UserResponse;
import com.school.sma.utility.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userrequest);

	ResponseEntity<ResponseStructure<UserResponse>> findUser(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, UserRequest userrequest);
	
	

	ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(UserRequest request);

	ResponseEntity<ResponseStructure<UserResponse>> addOtherUser(UserRequest request);

}
