package com.school.studentmanagement.service;


import org.springframework.http.ResponseEntity;

import com.school.studentmanagement.enums.UserRole;
import com.school.studentmanagement.requestDTO.UserRequest;
import com.school.studentmanagement.responseDTO.UserResponse;
import com.school.studentmanagement.utility.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userrequest);

	ResponseEntity<ResponseStructure<UserResponse>> findUser(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, UserRequest userrequest);
	
	

	ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(UserRequest request);
	
	

	ResponseEntity<ResponseStructure<UserResponse>> addOtherUser(UserRequest request);

}
