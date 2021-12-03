package com.rewardpoints.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.rewardpoints.exception.UserNotFoundException;

public interface ISpringAccessPointsService {

	public ResponseEntity<?> getUserFromISpring(HttpHeaders headers, String userId) throws UserNotFoundException;
	
	public ResponseEntity<?> getAllUsersAndPointsFromISpring(HttpHeaders headers) throws UserNotFoundException;
	
}
