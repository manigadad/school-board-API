package com.school.sma.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sma.requestDTO.ClassHoursDTO;
import com.school.sma.responseDTO.ClassHourResponse;
import com.school.sma.utility.ResponseStructure;

public interface ClassHourService {

	ResponseEntity<ResponseStructure<String>> generateClassHourForAcademicProgram(int programId);

	ResponseEntity<ResponseStructure<List<ClassHourResponse>>> updateClassHour(List<ClassHoursDTO> classHourDTOlist);


}
