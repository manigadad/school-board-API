package com.school.studentmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
@Entity
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schoolId;
	private String schoolName;
	private long ContactNo;
	private String emailId;
	private String Address;
	
	@OneToOne
	private Schedule schedule;
	
	
	 @OneToMany(mappedBy = "school") 
	    List<AcademicProgram> aplist=new ArrayList<AcademicProgram>();
}
