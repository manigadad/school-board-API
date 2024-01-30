package com.school.sma.exception;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserNotFoundByIdException extends RuntimeException {

	private String message ;

}
