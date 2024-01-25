package com.school.studentmanagement.requestDTO;


import com.school.studentmanagement.enums.UserRole;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data

public class UserRequest {

//@Pattern(regexp="^[a-zA-Z0-9]*$", message = "Invalid username")
//	@NotEmpty(message = "Username cannot be null/empty")
	private String username;

//	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
//	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
//	@NotEmpty( message = "Pass"
//			+ "word must contain at least one letter, one number, one special character")
	private String password;

	private String firstName;
	private String lastName;

	private String contactNo;

	
//	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "Invalid email")
//	@NotEmpty(message = "Email cannot be blank/empty")
	private String eMail;

	private UserRole userRole;
}

