package com.school.studentmanagement.responseDTO;

import com.school.studentmanagement.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse {

	private int userId;
	private String username;
	private String firstName;
	private String lastName;
	private String contactNo;
	private String eMail;
	private UserRole userRole;
	
	private boolean isDeleted;
	

}
