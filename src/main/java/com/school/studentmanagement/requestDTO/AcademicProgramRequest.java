package com.school.studentmanagement.requestDTO;

import java.time.LocalDate;

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
public class AcademicProgramRequest {

	private ProgramType programtype;
	private String ProgramName;
	private LocalDate beginsAt;
	private LocalDate endsAt;

}
