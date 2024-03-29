package com.school.sma.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchoolRequest {

	
	private String schoolName;
	private long ContactNo;
	private String emailId;
	private String Address;
	private boolean isDeleted;
}
