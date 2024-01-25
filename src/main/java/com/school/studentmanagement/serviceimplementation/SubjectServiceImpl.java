package com.school.studentmanagement.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.studentmanagement.entity.Subject;
import com.school.studentmanagement.enums.ProgramType;
import com.school.studentmanagement.exception.IllegalRequestException;
import com.school.studentmanagement.repository.AcademicProgramRepository;
import com.school.studentmanagement.repository.SubjectRepository;
import com.school.studentmanagement.repository.UserRepository;
import com.school.studentmanagement.requestDTO.SubjectRequest;
import com.school.studentmanagement.responseDTO.AcademicProgramResponse;
import com.school.studentmanagement.responseDTO.SubjectResponse;
import com.school.studentmanagement.service.SubjectService;
import com.school.studentmanagement.utility.ResponseStructure;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectrepository;
	
	@Autowired
	private AcademicProgramRepository academicProgramRepository;
	
	@Autowired
	private ResponseStructure<AcademicProgramResponse> structure;
	
	@Autowired
	private AcademicProgramServiceImpl academicprogramserviceimpl;
	
    @Autowired
    private UserRepository userrepository;
    
    
	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addSubject(int programId, SubjectRequest subjectRequest) {
		return academicProgramRepository.findById(programId)
				.map(program -> {
					List<Subject> subjects = (program.getSlist() != null) ? program.getSlist() : new ArrayList<>();

					// Add new subjects specified by the client
					subjectRequest.getSubjectName().forEach(name -> {
						boolean isPresent = subjects.stream().anyMatch(subject -> name.equalsIgnoreCase(subject.getSubjectName()));
						if (!isPresent) {
							subjects.add(subjectrepository.findBySubjectName(name)
									.orElseGet(() -> subjectrepository.save(Subject.builder().subjectName(name).build())));
						}
					});

					// Remove subjects that are not specified by the client
					List<Subject> toBeRemoved = new ArrayList<>();
					subjects.forEach(subject -> {
						boolean isPresent = subjectRequest.getSubjectName().stream()
								.anyMatch(name -> subject.getSubjectName().equalsIgnoreCase(name));
						if (!isPresent) {
							toBeRemoved.add(subject);
						}
					});
					subjects.removeAll(toBeRemoved);

					program.setSlist(subjects);
					academicProgramRepository.save(program);

					structure.setStatus(HttpStatus.CREATED.value());
					structure.setMessage("Created the subject list for the Academic Program");
							structure.setData(academicprogramserviceimpl.mapToAcademicProgramResponse(program));
							return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(structure, HttpStatus.CREATED);
				})
				.orElseThrow(() -> new IllegalRequestException("Academic Program not found"));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<SubjectResponse>>> findAllSubjects() {
	    List<Subject> subjects = subjectrepository.findAll();

	    // Return the response
	    ResponseStructure<List<SubjectResponse>> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatus(HttpStatus.OK.value());
	    responseStructure.setMessage("Subjects fetched successfully");
	    responseStructure.setData(mapToSubjectResponses(subjects));
	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
	
	private List<SubjectResponse> mapToSubjectResponses(List<Subject> subjects) {
		return subjects.stream()
                .map(subject -> new SubjectResponse(subject.getSubjectId(), subject.getSubjectName()))
                .collect(Collectors.toList());
	}


}
