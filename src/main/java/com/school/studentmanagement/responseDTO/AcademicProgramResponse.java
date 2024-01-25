package com.school.studentmanagement.responseDTO;

import java.time.LocalDate;
import java.util.List;

import com.school.studentmanagement.entity.Subject;
import com.school.studentmanagement.enums.ProgramType;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademicProgramResponse {

	private int programId;
	private ProgramType programtype;
	private String ProgramName;
	private LocalDate beginsAt;
	private LocalDate endsAt;
	List<Subject> Slist;
}
