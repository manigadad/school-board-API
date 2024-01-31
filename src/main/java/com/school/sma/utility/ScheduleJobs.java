package com.school.sma.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.school.sma.enums.UserRole;
import com.school.sma.repository.AcademicProgramRepository;
import com.school.sma.repository.ClassHourRepository;
import com.school.sma.repository.UserRepository;
import com.school.sma.serviceimplementation.AcademicProgramServiceImpl;
import com.school.sma.serviceimplementation.SchoolServiceImpl;
import com.school.sma.serviceimplementation.UserServiceImpl;

import jakarta.transaction.Transactional;

@Component
public class ScheduleJobs {

//	@Scheduled(fixedDelay = 1000l)
//	public void test() {
//		System.out.println("Schedule jobs");
//	}
	@Autowired
	private SchoolServiceImpl schoolServiceImpl;
	
	@Autowired
	private AcademicProgramServiceImpl academicServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceimpl;
	
//	@Transactional
//	@Scheduled(fixedDelay = 1000l)
//	public void test() {
//		userServiceimpl.deleteUsers();
//	}
//	
//	@Transactional
//	@Scheduled(fixedDelay = 1000l)
//	public void test1() {
//		academicServiceImpl.deleteprograms();
//	}
//	
//	@Transactional
//	@Scheduled(fixedDelay = 1000l)
//	public void test2() {
//		schoolServiceImpl.deleteSchool(1);
//	}
}
