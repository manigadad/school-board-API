package com.school.sma.serviceimplementation;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sma.entity.ClassHour;
import com.school.sma.entity.Schedule;
import com.school.sma.entity.School;
import com.school.sma.entity.Subject;
import com.school.sma.entity.User;
import com.school.sma.enums.ClassStatus;
import com.school.sma.enums.UserRole;
import com.school.sma.exception.IllegalRequestException;
import com.school.sma.repository.AcademicProgramRepository;
import com.school.sma.repository.ClassHourRepository;
import com.school.sma.repository.SubjectRepository;
import com.school.sma.repository.UserRepository;
import com.school.sma.requestDTO.ClassHoursDTO;
import com.school.sma.responseDTO.ClassHourResponse;
import com.school.sma.service.ClassHourService;
import com.school.sma.utility.ResponseStructure;

@Service
public class ClassHourImpl implements ClassHourService{


	@Autowired
	private AcademicProgramRepository academicProgramRepository;
	@Autowired
	private ClassHourRepository classHourRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private UserRepository userRepository;

	private boolean isBreakTime(LocalDateTime currentTime , Schedule schedule)
	{
		LocalTime breakTimeStart = schedule.getBreakTime();
		LocalTime breakTimeEnd = breakTimeStart.plusMinutes(schedule.getBreakLengthInMinutes().toMinutes());

		return (currentTime.toLocalTime().isAfter(breakTimeStart) && currentTime.toLocalTime().isBefore(breakTimeEnd));

	}

	private boolean isLunchTime(LocalDateTime currentTime , Schedule schedule)
	{
		LocalTime lunchTimeStart = schedule.getLunchTime();
		LocalTime lunchTimeEnd = lunchTimeStart.plusMinutes(schedule.getLunchLengthInMinutes().toMinutes());

		return (currentTime.toLocalTime().isAfter(lunchTimeStart) && currentTime.toLocalTime().isBefore(lunchTimeEnd));

	}

	@Override
	public ResponseEntity<ResponseStructure<String>> generateClassHourForAcademicProgram(int programId) {
		return academicProgramRepository.findById(programId)
				.map(academicProgram -> {
					School school = academicProgram.getSchool();
					Schedule schedule = school.getSchedule();
					if (schedule != null) {
						int classHourPerDay = schedule.getClassHoursPerDay();
						int classHourLength = (int) schedule.getClassHoursLengthInMinutes().toMinutes();

						LocalDateTime currentTime = LocalDateTime.now().with(schedule.getOpensAt());

						LocalTime lunchTimeStart = schedule.getLunchTime();
						LocalTime lunchTimeEnd = lunchTimeStart.plusMinutes(schedule.getLunchLengthInMinutes().toMinutes());
						LocalTime breakTimeStart = schedule.getBreakTime();
						LocalTime breakTimeEnd = breakTimeStart.plusMinutes(schedule.getBreakLengthInMinutes().toMinutes());

						for (int day = 1; day <= 6; day++) {
							for (int hour = 0; hour < classHourPerDay + 2; hour++) {
								ClassHour classHour = new ClassHour();

								if (!currentTime.toLocalTime().equals(lunchTimeStart) && !isLunchTime(currentTime, schedule)) {
									if (!currentTime.toLocalTime().equals(breakTimeStart) && !isBreakTime(currentTime, schedule)) {
										LocalDateTime beginsAt = currentTime;
										LocalDateTime endsAt = beginsAt.plusMinutes(classHourLength);

										classHour.setBeginsAt(beginsAt);
										classHour.setEndsAt(endsAt);
										classHour.setClassstatus(ClassStatus.NOTSCHEDULED);
										classHour.setRoomNo(1);
										currentTime = endsAt;
									} else {
										classHour.setBeginsAt(currentTime);
										classHour.setEndsAt(currentTime.plusMinutes(schedule.getBreakLengthInMinutes().toMinutes()));
										classHour.setClassstatus(ClassStatus.BREAK_TIME);
										classHour.setRoomNo(1);
										currentTime = currentTime.plusMinutes(schedule.getBreakLengthInMinutes().toMinutes());
									}
								} else {
									classHour.setBeginsAt(currentTime);
									classHour.setEndsAt(currentTime.plusMinutes(schedule.getLunchLengthInMinutes().toMinutes()));
									classHour.setClassstatus(ClassStatus.LUNCH_TIME);
									classHour.setRoomNo(1);
									currentTime = currentTime.plusMinutes(schedule.getLunchLengthInMinutes().toMinutes());
								}
								classHour.setAcademicProgram(academicProgram);
								classHourRepository.save(classHour);
							}
							currentTime = currentTime.plusDays(1).with(schedule.getOpensAt());
						}
					} else {
						throw new IllegalRequestException("The school does not contain any schedule, please provide a schedule to the school");
					}

					return ResponseEntity.ok(ResponseStructure.<String>builder()
							.status(HttpStatus.CREATED.value())
							.message("ClassHour generated successfully for the academic program")
							.data("Class Hour generated for the current week successfully")
							.build());
				})
				.orElseThrow(() -> new IllegalRequestException("Invalid Program Id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ClassHourResponse>>> updateClassHour(List<ClassHoursDTO> classHourDTOlist) {
	    List<ClassHourResponse> updatedClassHourResponses = new ArrayList<>();

	    classHourDTOlist.forEach(classHourDTO -> {
	        try {
	            ClassHour existingClassHour = classHourRepository.findById(classHourDTO.getClassHourId())
	                    .orElseThrow(() -> new IllegalRequestException("Invalid Class Hour Id"));

	            Subject subject = subjectRepository.findById(classHourDTO.getSubjectId())
	                    .orElseThrow(() -> new IllegalRequestException("Invalid Subject Id"));

	            User teacher = userRepository.findById(classHourDTO.getTeacherId())
	                    .orElseThrow(() -> new IllegalRequestException("Invalid Teacher Id"));

	            if (teacher.getUserRole().equals(UserRole.TEACHER)) {
	                if (!teacher.getSubject().equals(subject)) {
	                    throw new IllegalRequestException("The Teacher is Not Teaching That Subject");
	                }

	                existingClassHour.setSubject(subject);
	                existingClassHour.setUser(teacher);
	                existingClassHour.setRoomNo(classHourDTO.getRoomNo());

	                LocalDateTime currentTime = LocalDateTime.now();

	                if (existingClassHour.getBeginsAt().isBefore(currentTime) && existingClassHour.getEndsAt().isAfter(currentTime)) {
	                    existingClassHour.setClassstatus(ClassStatus.ONGOING);
	                } else if (existingClassHour.getEndsAt().isBefore(currentTime)) {
	                    existingClassHour.setClassstatus(ClassStatus.COMPLETED);
	                } else {
	                    existingClassHour.setClassstatus(ClassStatus.SCHEDULED);
	                }

	                existingClassHour = classHourRepository.save(existingClassHour);

	                ClassHourResponse classHourResponse = new ClassHourResponse();
	                classHourResponse.setBeginsAt(existingClassHour.getBeginsAt());
	                classHourResponse.setEndsAt(existingClassHour.getEndsAt());
	                classHourResponse.setClassStatus(existingClassHour.getClassstatus());
	                classHourResponse.setRoomNo(existingClassHour.getRoomNo());
	                updatedClassHourResponses.add(classHourResponse);

	            } else {
	                throw new IllegalRequestException("Invalid Teacher Id.");
	            }
	        } catch (Exception e) {
	            // Handle exceptions and log the error
	            e.printStackTrace();
	        }
	    });

	    ResponseStructure<List<ClassHourResponse>> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatus(HttpStatus.CREATED.value());
	    responseStructure.setMessage("ClassHours updated successfully!!!!");
	    responseStructure.setData(updatedClassHourResponses);

	    return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}
}




