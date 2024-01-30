package com.school.sma.responseDTO;

import java.time.LocalDateTime;

import com.school.sma.enums.ClassStatus;

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
