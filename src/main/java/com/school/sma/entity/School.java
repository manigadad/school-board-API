package com.school.sma.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Getter
@Setter
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schoolId;
	private String schoolName;
	private long ContactNo;
	private String emailId;
	private String Address;
	
	private boolean isDeleted;

	@OneToOne
	private Schedule schedule;

	@OneToMany(mappedBy = "school") 
	List<AcademicProgram> aplist=new ArrayList<AcademicProgram>();
	
	@OneToMany(mappedBy = "school")
	private List<User> users;
}
