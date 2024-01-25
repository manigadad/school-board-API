package com.school.studentmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.studentmanagement.requestDTO.SubjectRequest;
import com.school.studentmanagement.responseDTO.AcademicProgramResponse;
import com.school.studentmanagement.responseDTO.SubjectResponse;
import com.school.studentmanagement.responseDTO.UserResponse;
import com.school.studentmanagement.utility.ResponseStructure;

public interface SubjectService {

	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addSubject(int  programId,SubjectRequest subjectrequest);
	//
//		ResponseEntity<ResponseStructure<AcademicProgramResponse>> updateSubject(int programId,
//				SubjectRequest subjectRequest);

		public ResponseEntity<ResponseStructure<List<SubjectResponse>>> findAllSubjects();

		public ResponseEntity<ResponseStructure<AcademicProgramResponse>> assignSubjectToTeacher(int subjectId,
				int userId);

		public ResponseEntity<ResponseStructure<UserResponse>> assignSubjectToUser(int subjectId, int userId);

	
}
