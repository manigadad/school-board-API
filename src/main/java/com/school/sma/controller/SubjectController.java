package com.school.sma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sma.requestDTO.SubjectRequest;
import com.school.sma.responseDTO.AcademicProgramResponse;
import com.school.sma.responseDTO.UserResponse;
import com.school.sma.service.SubjectService;
import com.school.sma.utility.ResponseStructure;

@RestController
public class SubjectController {

	@Autowired
	private SubjectService subjectservice;
	
	
	@PostMapping("/academicprograms/{programId}/subjects")
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addSubject(@PathVariable int  programId,@RequestBody SubjectRequest subjectrequest){
		return subjectservice.addSubject(programId,subjectrequest);
	}
	
	@PutMapping("/subjects/{subjectId}/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> assignSubjectToTeacher(@PathVariable int subjectId,@PathVariable int userId){
		return subjectservice.assignSubjectToUser(subjectId,userId);
	}
	
}
