package com.school.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.studentmanagement.requestDTO.AcademicProgramRequest;
import com.school.studentmanagement.responseDTO.AcademicProgramResponse;
import com.school.studentmanagement.service.AcademicProgramService;
import com.school.studentmanagement.utility.ResponseStructure;

@RestController
public class AcademicProgramController {

	@Autowired
	private AcademicProgramService academicProgramservice;
	
	@PostMapping("/schools/{schoolId}/academicprograms")
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> saveAcademicProgram(@PathVariable int schoolId,@RequestBody AcademicProgramRequest academicprogramrequest ){
		return academicProgramservice.saveacademicprogram(schoolId, academicprogramrequest);
	}
	@GetMapping("/schools/{schoolId}/academicprograms")
	public List<AcademicProgramResponse> findallAcademicPrograms(@PathVariable int schoolId){
		return academicProgramservice.findallAcademicPrograms(schoolId);
	}
   @PutMapping("/academic-programs/{programId}/users/{userId}")
   private ResponseEntity<ResponseStructure<AcademicProgramResponse>> assignUser(@PathVariable int userId,@PathVariable int programId){
	   return academicProgramservice.assignUser(userId,programId);
   }
   
   private ResponseEntity<ResponseStructure<AcademicProgramResponse>> assignUserToAcademicProgram(@PathVariable int programId,@PathVariable int userId,@PathVariable AcademicProgramRequest academicprogramrequest ){
	   
	   return academicProgramservice.assignUserToAcademicProgram(programId,userId,academicprogramrequest);
   }
}
