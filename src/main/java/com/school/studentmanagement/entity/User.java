package com.school.studentmanagement.entity;


import java.util.List;

import com.school.studentmanagement.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(unique = true)
	private String username;
	private String firstName;
	private String lastName;
	private String contactNo;
	@Column(unique = true)
	
	private String eMail;
	private String password;
	private UserRole userRole;
	
	private boolean isDeleted;
	
	@ManyToOne
	private School school;
	
	@ManyToOne
	private Subject subject;
	
	@ManyToMany(mappedBy ="Userlist")
	private List<AcademicProgram> Aprogramlist;
	
	@OneToMany(mappedBy = "user")
	private List<ClassHour> classHours;
	
	
} 
