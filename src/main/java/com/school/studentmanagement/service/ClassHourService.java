package com.school.studentmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.studentmanagement.requestDTO.ClassHoursDTO;
import com.school.studentmanagement.responseDTO.ClassHourResponse;
import com.school.studentmanagement.utility.ResponseStructure;

public interface ClassHourService {

	ResponseEntity<ResponseStructure<String>> generateClassHourForAcademicProgram(int programId);

	ResponseEntity<ResponseStructure<List<ClassHourResponse>>> updateClassHour(List<ClassHoursDTO> classHourDTOlist);


}
