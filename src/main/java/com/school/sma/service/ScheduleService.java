package com.school.sma.service;

import org.springframework.http.ResponseEntity;

import com.school.sma.requestDTO.ScheduleRequest;
import com.school.sma.responseDTO.ScheduleResponse;
import com.school.sma.utility.ResponseStructure;

public interface ScheduleService {

	ResponseEntity<ResponseStructure<ScheduleResponse>> saveSchedule(int schoolId, ScheduleRequest schedulerequest);

	ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(int schoolId);

	ResponseEntity<ResponseStructure<ScheduleResponse>> updateSchedule(int scheduleId, ScheduleRequest schedulerequest);

	void deleteSchedule(int scheduleId);

}
