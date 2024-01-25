package com.school.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.studentmanagement.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>{

	Optional<Subject> findBySubjectName(String name);

}
