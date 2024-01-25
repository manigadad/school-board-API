package com.school.studentmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.studentmanagement.requestDTO.AcademicProgramRequest;
import com.school.studentmanagement.responseDTO.AcademicProgramResponse;
import com.school.studentmanagement.utility.ResponseStructure;

public interface AcademicProgramService {

	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> saveacademicprogram(int schoolId,AcademicProgramRequest academicprogramrequest);

//	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> findallAcademicProgram(int schoolId);

	List<AcademicProgramResponse> findallAcademicPrograms(int schoolId);

	 public ResponseEntity<ResponseStructure<AcademicProgramResponse>> assignUser(int userId,int programId);

	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> assignUserToAcademicProgram(int programId,
			int userId, AcademicProgramRequest academicprogramrequest);

}
