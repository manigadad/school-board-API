package com.school.sma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.sma.entity.AcademicProgram;

@Repository
public interface AcademicProgramRepository extends JpaRepository<AcademicProgram, Integer> {

	List<AcademicProgram> findByIsDeleted(boolean isDeleted);

	

}
