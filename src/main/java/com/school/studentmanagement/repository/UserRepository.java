package com.school.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.studentmanagement.entity.User;
import com.school.studentmanagement.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByusername(String username);

	Optional<User> findUserByUserRole(UserRole admin);

	
}
