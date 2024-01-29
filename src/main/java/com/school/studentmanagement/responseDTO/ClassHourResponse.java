package com.school.studentmanagement.responseDTO;

import java.time.LocalDateTime;

import com.school.studentmanagement.enums.ClassStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassHourResponse {

	
	private LocalDateTime beginsAt;
	private LocalDateTime endsAt;
	private int roomNo;
	
	private ClassStatus classStatus;
	
}
