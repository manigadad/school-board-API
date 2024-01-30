package com.school.sma.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.sma.entity.User;
import com.school.sma.enums.UserRole;
import com.school.sma.exception.UserNotFoundByIdException;
import com.school.sma.repository.UserRepository;
import com.school.sma.requestDTO.UserRequest;
import com.school.sma.responseDTO.UserResponse;
import com.school.sma.service.UserService;
import com.school.sma.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private ResponseStructure<UserResponse> responseStructure;
	
	
	

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userrequest) {
		// Fetch existing users from the repository
		List<User> existingUsers = repository.findAll();

		// Check if the requested role is ADMIN and if an ADMIN already exists
		if (userrequest.getUserRole() == UserRole.ADMIN && isAdminExists(existingUsers)) {
			throw new IllegalArgumentException("An ADMIN user already exists.");
		}
		User user = (User) repository.save(mapToUser(userrequest, false));
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User saved Sucessfully");
		responseStructure.setData(mapToUserResponse(user,false));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(int userId) {
		return repository.findById(userId)
				.map(user->{
					responseStructure.setStatus(HttpStatus.FOUND.value());
					responseStructure.setMessage("user fetch susscessfully");
					responseStructure.setData(mapToUserResponse(user,false));
					return new ResponseEntity<>(responseStructure,HttpStatus.FOUND);
				})
				.orElseThrow (()-> new UserNotFoundByIdException("user not found by id"));
	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {

		return repository.findById(userId)
				.map(user -> {
					user.setDeleted(true);
					//					userrepository.deleteById(userId);
					responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMessage("user deleted successfully");
					responseStructure.setData(mapToUserResponse(user,true));

					return new ResponseEntity<>(responseStructure, HttpStatus.OK);
				})
				.orElseThrow(() -> new UserNotFoundByIdException("User not found by id"));
	}

	private User mapToUser(UserRequest request, boolean isDeleted) {
		return User.builder()
				.username(request.getUsername())
				.eMail(request.getEMail())
				.password(encoder.encode(request.getPassword()))
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.contactNo(request.getContactNo())
				.userRole(request.getUserRole())
				.isDeleted(isDeleted)
				.build();
	}
	public  UserResponse mapToUserResponse(User user, boolean isDeleted) {
		return UserResponse.builder()
				.userId(user.getUserId())
				.username(user.getUsername())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.contactNo(user.getContactNo())
				.eMail(user.getEMail())
				.userRole(user.getUserRole())
				.isDeleted(user.isDeleted())
				.build();


	}
	private boolean isAdminExists(List<User> users) {
		for (User user : users) {
			if (user.getUserRole() == UserRole.ADMIN) {
				return true;
			}
		}

		return false;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId ,UserRequest userrequest) {

		User user = repository.findById(userId)
				.map(existingUser -> {
					User updatedUser = mapToUser(userrequest,false);
					updatedUser.setUserId(userId);
					return repository.save(updatedUser);
				})
				.orElseThrow(() -> new UserNotFoundByIdException("User not found by id"));

		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("User updated successfully");
		structure.setData(mapToUserResponse(user,false));

		return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(UserRequest request) {


		if(request.getUserRole()==UserRole.ADMIN) {
			// Fetch existing users from the repository
			List<User> existingUsers = repository.findAll();

			// Check if the requested role is ADMIN and if an ADMIN already exists
			if (request.getUserRole() == UserRole.ADMIN && isAdminExists(existingUsers)) {
				throw new IllegalArgumentException("An ADMIN user already exists.");
			}
			
		}
		else {
			throw new IllegalArgumentException("only Admin can register");
		}
		User user = repository.save(mapToUser(request,false));
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User saved Sucessfully");
		responseStructure.setData(mapToUserResponse(user,false));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);

	}

	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addOtherUser(UserRequest request) {
		// Fetch existing users from the repository
				List<User> existingUsers = repository.findAll();

				// Check if the requested role is ADMIN and if an ADMIN already exists
				if (request.getUserRole() == UserRole.ADMIN && isAdminExists(existingUsers)) {
					throw new IllegalArgumentException("An ADMIN user already exists.");
				}
				
				User user = repository.save(mapToUser(request,false));
				
				if (user.getUserRole() == UserRole.TEACHER || user.getUserRole() == UserRole.STUDENT) {
					mapUserToAdminSchool(user);
		        }
				
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("User saved Sucessfully");
				responseStructure.setData(mapToUserResponse(user,false));
				return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
			
	}
	
	

	private void mapUserToAdminSchool(User user) {
		// Find the admin user
		User admin =repository.findUserByUserRole(UserRole.ADMIN)
				.orElseThrow(() -> new IllegalStateException("Admin user not found."));

		// Map the user to the same school as the admin
		user.setSchool(admin.getSchool());
		repository.save(user);

	}
	



}
