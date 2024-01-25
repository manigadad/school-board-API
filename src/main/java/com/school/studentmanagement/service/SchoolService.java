package com.school.studentmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.school.studentmanagement.entity.School;
import com.school.studentmanagement.entity.User;
import com.school.studentmanagement.requestDTO.SchoolRequest;
import com.school.studentmanagement.responseDTO.SchoolResponse;
import com.school.studentmanagement.utility.ResponseStructure;

public interface SchoolService {

	ResponseEntity<ResponseStructure<SchoolResponse>> saveSchoool(SchoolRequest schoolrequest);

	Optional<School> findSchool(int schoolId);

	School updatedSchool(int schoolId, School updatedschool);

	void deleteSchool(int schoolId);

	
}
