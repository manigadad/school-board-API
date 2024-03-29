package com.school.sma.controller;

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

import com.school.sma.entity.School;
import com.school.sma.requestDTO.SchoolRequest;
import com.school.sma.responseDTO.SchoolResponse;
import com.school.sma.service.SchoolService;
import com.school.sma.utility.ResponseStructure;

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
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/schools/{schoolId}")
	public ResponseEntity<ResponseStructure<SchoolResponse>>  deleteSchool(@PathVariable int schoolId) {
		return schoolservice.deleteSchool(schoolId);
	
	}
	

	
}
