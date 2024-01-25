package com.school.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.studentmanagement.requestDTO.SubjectRequest;
import com.school.studentmanagement.responseDTO.AcademicProgramResponse;
import com.school.studentmanagement.service.SubjectService;
import com.school.studentmanagement.utility.ResponseStructure;

@RestController
public class SubjectController {

	@Autowired
	private SubjectService subjectservice;
	@PostMapping("/academicprograms/{programId}/subjects")
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addSubject(@PathVariable int  programId,@RequestBody SubjectRequest subjectrequest){
		return subjectservice.addSubject(programId,subjectrequest);
	}
	
	
}
