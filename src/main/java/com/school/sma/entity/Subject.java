package com.school.sma.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Getter
@Setter
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subjectId;
	
	private String subjectName;
	
	@OneToMany(mappedBy = "subject")
	private List<ClassHour> classHours;
}
