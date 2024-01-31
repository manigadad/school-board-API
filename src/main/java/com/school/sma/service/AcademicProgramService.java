package com.school.sma.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sma.entity.User;
import com.school.sma.enums.UserRole;
import com.school.sma.requestDTO.AcademicProgramRequest;
import com.school.sma.responseDTO.AcademicProgramResponse;
import com.school.sma.responseDTO.UserResponse;
import com.school.sma.utility.ResponseStructure;

public interface AcademicProgramService {

	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> saveacademicprogram(int schoolId,AcademicProgramRequest academicprogramrequest);

//	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> findallAcademicProgram(int schoolId);

	List<AcademicProgramResponse> findallAcademicPrograms(int schoolId);

	 public ResponseEntity<ResponseStructure<AcademicProgramResponse>> assignUser(int userId,int programId);

	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> assignUserToAcademicProgram(int programId,
			int userId, AcademicProgramRequest academicprogramrequest);


	ResponseEntity<ResponseStructure<List<User>>> fetchUsersByRoleInAcademicProgram(int programId, String role);

	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> deleteAcademicProgram(int programId);


}
