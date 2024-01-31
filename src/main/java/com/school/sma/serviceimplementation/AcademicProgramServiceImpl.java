package com.school.sma.serviceimplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sma.entity.AcademicProgram;
import com.school.sma.entity.School;
import com.school.sma.entity.User;
import com.school.sma.enums.UserRole;
import com.school.sma.exception.IllegalRequestException;
import com.school.sma.exception.UserNotFoundByIdException;
import com.school.sma.repository.AcademicProgramRepository;
import com.school.sma.repository.ClassHourRepository;
import com.school.sma.repository.SchoolRepository;
import com.school.sma.repository.UserRepository;
import com.school.sma.requestDTO.AcademicProgramRequest;
import com.school.sma.responseDTO.AcademicProgramResponse;
import com.school.sma.responseDTO.UserResponse;
import com.school.sma.service.AcademicProgramService;
import com.school.sma.utility.ResponseStructure;

@Service
public class AcademicProgramServiceImpl implements AcademicProgramService{

	@Autowired
	private  AcademicProgramRepository academicprogramrepository;
	@Autowired
	private ResponseStructure<AcademicProgramResponse> structure;
	@Autowired
	private SchoolRepository schoolrepository;
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private ClassHourRepository classhourrepository;



	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> saveacademicprogram(int schoolId,
			AcademicProgramRequest academicprogramrequest) {
		return schoolrepository.findById(schoolId).map(s -> {
			AcademicProgram academicProgram = mapToAcademicProgram(academicprogramrequest);
			academicProgram.setSchool(s); // Set the school for the program
			academicProgram = academicprogramrepository.save(academicProgram);
			s.getAplist().add(academicProgram); // Add to the existing list
			schoolrepository.save(s);

			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Academic Program object created Successfully");
			structure.setData(mapToAcademicProgramResponse(academicProgram, false));
			return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(structure, HttpStatus.CREATED);
		}).orElseThrow(() -> new IllegalRequestException("School not found"));
	}

	@Override
	public List<AcademicProgramResponse> findallAcademicPrograms(int schoolId) {
		java.util.Optional<School> optionalSchool = schoolrepository.findById(schoolId);
		if (optionalSchool.isPresent()) {
			School school = optionalSchool.get();
			List<AcademicProgram> academicPrograms = school.getAplist();
			List<AcademicProgramResponse> responses = new ArrayList<>();
			for (AcademicProgram academicProgram : academicPrograms) {
				responses.add(mapToAcademicProgramResponse(academicProgram, false));
			}
			return responses;
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> assignUser(
			int userId,int programId) {
		AcademicProgram academicProgram = academicprogramrepository.findById(programId)
				.orElseThrow(() -> new IllegalRequestException("Academic Program not found"));

		// Validate the user
		User user = userrepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found"));

		// Check if the user is an ADMIN
		if (user.getUserRole() == UserRole.ADMIN) {
			throw new IllegalRequestException("Admin cannot be associated with any Academic Program");
		}

		// Determine the role of the user in the Academic Program
		UserRole userRoleInProgram = determineUserRoleForAcademicProgram(user);

		// Assign the user to the Academic Program using lambda expressions and map method
		academicProgram.getUserlist().addAll(
				userRoleInProgram == UserRole.TEACHER ?
						Collections.singletonList(user) : Collections.emptyList()
				);

		academicProgram.getUserlist().addAll(
				userRoleInProgram == UserRole.STUDENT ?
						Collections.singletonList(user) : Collections.emptyList()
				);

		academicprogramrepository.save(academicProgram);

		// Return the response
		ResponseStructure<AcademicProgramResponse> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("User assigned to Academic Program successfully");
		structure.setData(mapToAcademicProgramResponse(academicProgram, false));
		return ResponseEntity.ok(structure);
	}

	private UserRole determineUserRoleForAcademicProgram(User user) {
		// You may implement your logic to determine the role of the user in the Academic Program
		// For example, check user roles, or based on some other business logic.
		// This is just a placeholder, adapt it based on your actual requirements.
		return user.getUserRole();
	}


	private AcademicProgram mapToAcademicProgram(AcademicProgramRequest academicprogramrequest) {
		return AcademicProgram.builder()
				.ProgramName(academicprogramrequest.getProgramName())
				.programtype(academicprogramrequest.getProgramtype())
				.beginsAt(academicprogramrequest.getBeginsAt())
				.endsAt(academicprogramrequest.getEndsAt())
				.build();
	}
	public AcademicProgramResponse mapToAcademicProgramResponse(AcademicProgram academicProgram, boolean b) {
		return AcademicProgramResponse.builder()
				.programId(academicProgram.getProgramId())
				.programtype(academicProgram.getProgramtype())
				.ProgramName(academicProgram.getProgramName())
				.beginsAt(academicProgram.getBeginsAt())
				.endsAt(academicProgram.getEndsAt())
				.Slist(academicProgram.getSlist())
				.isDeleted(academicProgram.isDeleted())
				.build();

	}

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> assignUserToAcademicProgram(int programId,
			int userId, AcademicProgramRequest academicprogramrequest) {

		AcademicProgram academicProgram = academicprogramrepository.findById(programId)
				.orElseThrow(() -> new IllegalRequestException("Academic Program not found"));

		// Validate the user
		User user = userrepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found"));

		// Check if the user is an ADMIN
		if (user.getUserRole() == UserRole.ADMIN) {
			throw new IllegalRequestException("Admin cannot be associated with any Academic Program");
		}
		if (user.getUserRole() != UserRole.TEACHER && user.getUserRole() != UserRole.STUDENT) {
			throw new IllegalRequestException("User must have role TEACHER or STUDENT.");
		}

		// Add the user to the academic program
		if (!academicProgram.getUserlist().contains(user)) {
			academicProgram.getUserlist().add(user);
			academicprogramrepository.save(academicProgram);

			// Return the response
			ResponseStructure<AcademicProgramResponse> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("User assigned to Academic Program successfully");
			structure.setData(mapToAcademicProgramResponse(academicProgram, false));
			return ResponseEntity.ok(structure);
		} else {
			throw new IllegalRequestException("User is already associated with the academic program");
		}


	}

	@Override

	public ResponseEntity<ResponseStructure<List<User>>> fetchUsersByRoleInAcademicProgram (int programId,String role) {

		List<User> users = academicprogramrepository.findById(programId).map(program -> userrepository.findByUserRoleAndListAcademicPrograms(UserRole.valueOf(role.toUpperCase()), program))
				.orElseThrow();



		ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Fetched successfully!!!");
		responseStructure.setData (users);

		return new ResponseEntity<ResponseStructure<List<User>>> (responseStructure, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> deleteAcademicProgram(int programId) {
		return academicprogramrepository.findById(programId)
				.map(program -> {
					program.setDeleted(true);
					//					userrepository.deleteById(userId);
					academicprogramrepository.save(program);
					structure.setStatus(HttpStatus.OK.value());
					structure.setMessage("Academicprogram deleted successfully");
					structure.setData(mapToAcademicProgramResponse(program,true));

					return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(structure, HttpStatus.OK);
				})
				.orElseThrow(() -> new IllegalRequestException("Academicprogram not found by id"));

	}

	public void deleteprograms() {
		List<AcademicProgram> programs =academicprogramrepository.findByIsDeleted(true);
		programs.forEach(program -> {
			classhourrepository.deleteAll(program.getClassHours());
			academicprogramrepository.deleteAll(programs);
		});
	}
}