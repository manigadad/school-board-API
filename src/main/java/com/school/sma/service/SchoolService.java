package com.school.sma.service;


import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.school.sma.entity.School;
import com.school.sma.requestDTO.SchoolRequest;
import com.school.sma.responseDTO.SchoolResponse;
import com.school.sma.utility.ResponseStructure;

public interface SchoolService {

	ResponseEntity<ResponseStructure<SchoolResponse>> saveSchoool(SchoolRequest schoolrequest);

	Optional<School> findSchool(int schoolId);

	School updatedSchool(int schoolId, School updatedschool);

	ResponseEntity<ResponseStructure<SchoolResponse>> deleteSchool(int schoolId);

	


}
