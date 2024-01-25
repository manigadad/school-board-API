package com.school.studentmanagement.entity;

import java.time.Duration;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int scheduleId;
	private LocalTime opensAt;
	private LocalTime closesAt;
	private int classHoursPerDay;
	private Duration classHoursLengthInMinutes;
	private LocalTime breakTime;
	private Duration breakLengthInMinutes;
	private LocalTime lunchTime;
	private Duration lunchLengthInMinutes;
	
	 public int getClassHoursLengthInMinutes() {
	        return (int) classHoursLengthInMinutes.toMinutes();
	    }

	    public int getBreakLengthInMinutes() {
	        return (int) breakLengthInMinutes.toMinutes();
	    }

	    public int getLunchLengthInMinutes() {
	        return (int) lunchLengthInMinutes.toMinutes();
	    }
}
