package com.school.studentmanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.studentmanagement.entity.School;
import com.school.studentmanagement.requestDTO.SchoolRequest;
import com.school.studentmanagement.responseDTO.SchoolResponse;
import com.school.studentmanagement.service.SchoolService;
import com.school.studentmanagement.utility.ResponseStructure;

@Controller
public class SchoolController {


	@Autowired
	private SchoolService schoolservice;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/schools")
	public ResponseEntity<ResponseStructure<SchoolResponse>> saveSchool(@RequestBody SchoolRequest schoolrequest) {
		return  schoolservice.saveSchoool( schoolrequest);
	}
	
	@ResponseBody
	@RequestMapping(value="/findschool",method=RequestMethod.GET)
	public Optional<School> findschool(@RequestParam int schoolId) {
		return schoolservice.findSchool(schoolId);
	
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseBody
	@RequestMapping(value="updateschool",method=RequestMethod.GET)
	public School updatedschool(@RequestParam int  schoolId,@RequestBody School updatedschool) {
		return schoolservice.updatedSchool(schoolId,updatedschool);
	
	}
	
	@DeleteMapping("/schools/{schoolId}")
	public void  deleteSchool(@PathVariable int schoolId) {
		schoolservice.deleteSchool(schoolId);
	
	}
	

	
}
