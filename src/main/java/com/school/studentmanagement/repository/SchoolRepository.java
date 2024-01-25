package com.school.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.studentmanagement.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {

}
