package com.school.sma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.sma.exception.UserNotFoundByIdException;
import com.school.sma.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByusername(username)
				.map(user->new CustomUserDetails(user)).orElseThrow(
				()-> new UserNotFoundByIdException("Failed to authenticate user"));
				
	}

	
}
