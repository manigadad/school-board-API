package com.school.sma.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sma.requestDTO.SubjectRequest;
import com.school.sma.responseDTO.AcademicProgramResponse;
import com.school.sma.responseDTO.SubjectResponse;
import com.school.sma.responseDTO.UserResponse;
import com.school.sma.utility.ResponseStructure;

public interface SubjectService {

	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addSubject(int  programId,SubjectRequest subjectrequest);
	//
//		ResponseEntity<ResponseStructure<AcademicProgramResponse>> updateSubject(int programId,
//				SubjectRequest subjectRequest);

		public ResponseEntity<ResponseStructure<List<SubjectResponse>>> findAllSubjects();

		public ResponseEntity<ResponseStructure<UserResponse>> assignSubjectToUser(int subjectId, int userId);

	
}
