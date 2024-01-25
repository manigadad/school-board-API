package com.school.studentmanagement.service;

import org.springframework.http.ResponseEntity;

import com.school.studentmanagement.requestDTO.ScheduleRequest;
import com.school.studentmanagement.responseDTO.ScheduleResponse;
import com.school.studentmanagement.utility.ResponseStructure;

public interface ScheduleService {

	ResponseEntity<ResponseStructure<ScheduleResponse>> saveSchedule(int schoolId, ScheduleRequest schedulerequest);

	ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(int schoolId);

	ResponseEntity<ResponseStructure<ScheduleResponse>> updateSchedule(int scheduleId, ScheduleRequest schedulerequest);

	void deleteSchedule(int scheduleId);

}
