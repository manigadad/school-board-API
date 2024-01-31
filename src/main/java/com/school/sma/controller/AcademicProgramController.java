package com.school.sma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sma.entity.User;
import com.school.sma.requestDTO.AcademicProgramRequest;
import com.school.sma.responseDTO.AcademicProgramResponse;
import com.school.sma.service.AcademicProgramService;
import com.school.sma.utility.ResponseStructure;

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
   @PutMapping("/users/{userId}/academic-programs/{programId}")
   private ResponseEntity<ResponseStructure<AcademicProgramResponse>> assignUser(@PathVariable int userId,@PathVariable int programId){
	   return academicProgramservice.assignUser(userId,programId);
   }
   
   
   @GetMapping("/academic-programs/{programId}/user-roles/{role}/users")
	public ResponseEntity<ResponseStructure<List<User>>> fetchUsersByRoleInAcademicProgram(@PathVariable int programId,@PathVariable String role){
		return academicProgramservice.fetchUsersByRoleInAcademicProgram(programId, role);
	}
   
   @DeleteMapping("/academic-programs/{programId}")
   public ResponseEntity<ResponseStructure<AcademicProgramResponse>> deleteAcademicProgram(@PathVariable int programId ){
		return academicProgramservice.deleteAcademicProgram(programId);
	}
   
}

