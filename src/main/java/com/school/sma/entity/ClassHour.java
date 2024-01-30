package com.school.sma.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.school.sma.enums.ClassStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor

public class ClassHour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int classHoursId;
	private LocalDateTime beginsAt;
	private LocalDateTime endsAt;
	private int roomNo;
	
	
	private ClassStatus classstatus;
	
	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private AcademicProgram academicProgram;
}
