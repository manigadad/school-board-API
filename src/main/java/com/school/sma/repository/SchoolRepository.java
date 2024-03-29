package com.school.sma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.sma.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {

}
