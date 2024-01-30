package com.school.sma.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.sma.entity.School;
import com.school.sma.enums.UserRole;
import com.school.sma.exception.IllegalRequestException;
import com.school.sma.exception.UserNotFoundByIdException;
import com.school.sma.repository.SchoolRepository;
import com.school.sma.repository.UserRepository;
import com.school.sma.requestDTO.SchoolRequest;
import com.school.sma.responseDTO.SchoolResponse;
import com.school.sma.service.SchoolService;
import com.school.sma.utility.ResponseStructure;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepository schoolrepository;
	@Autowired
	private ResponseStructure<SchoolResponse> structure;

	@Autowired
	UserRepository userrepository;
	@Override
	public ResponseEntity<ResponseStructure<SchoolResponse>> saveSchoool( SchoolRequest schoolrequest) {
	   String username= SecurityContextHolder.getContext().getAuthentication().getName();
	   
		return userrepository.findByusername(username).map(u->{
			if(u.getUserRole().equals(UserRole.ADMIN)) {
				if(u.getSchool()==null) {
					School school = mapToSchool(schoolrequest);
					school = schoolrepository.save(school);
					u.setSchool(school);
					userrepository.save(u);
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setMessage("school object created Sucessfully");
					structure.setData(mapToSchoolResponse(school));
					return new ResponseEntity<ResponseStructure<SchoolResponse>>(structure,HttpStatus.CREATED);

				}else
					throw new IllegalRequestException("school object is already present");

			}else
				throw new IllegalRequestException("Only admin can create the school");
		})
				.orElseThrow(()->new UserNotFoundByIdException());



	}



	//	@Override
	//	public Optional<School> findSchool(int schoolId) {
	//		// TODO Auto-generated method stub
	//		return Optional.empty();
	//	}

	@Override
	public School updatedSchool(int schoolId, School updatedschool) {
		
			Optional<School> optional = schoolrepository.findById(schoolId);
			if(optional.isPresent())
			{
				School existingschool= optional.get();
				updatedschool.setSchoolId(existingschool.getSchoolId());
				School school = schoolrepository.save(updatedschool);
				return school;  
			}
			else {
				return null;
			}
		}

		@Override
		public void deleteSchool(int schoolId) {
			
				// TODO Auto-generated method stub
				schoolrepository.deleteById(schoolId);
				
			}

		

		private School mapToSchool(SchoolRequest schoolrequest) {
			return School.builder()
					.schoolName(schoolrequest.getSchoolName())
					.ContactNo(schoolrequest.getContactNo())
					.emailId(schoolrequest.getEmailId())
					.Address(schoolrequest.getAddress())
					.build();
		}
		private SchoolResponse mapToSchoolResponse(School school) {
			return SchoolResponse.builder()
					.schoolName(school.getSchoolName())
					.contactNo(school.getContactNo())
					.emailId(school.getEmailId())
					.address(school.getAddress())
					.build();
		}



		@Override
		public Optional<School> findSchool(int schoolId) {
			
			return Optional.empty();
		}



	}
