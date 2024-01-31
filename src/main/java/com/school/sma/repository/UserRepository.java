package com.school.sma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.sma.entity.AcademicProgram;
import com.school.sma.entity.User;
import com.school.sma.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByusername(String username);

	Optional<User> findUserByUserRole(UserRole admin);

	List<User> findByUserRoleAndListAcademicPrograms(UserRole valueOf, AcademicProgram program);
	
	List<User> findByIsDeleted(boolean b);



}
