package com.rewardpoints.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rewardpoints.exception.UserNotFoundException;

@Named
public class ISpringAccessPointsServiceImpl implements ISpringAccessPointsService{
	
	@Value("${ispring.api.url}")
	private String ispringApiUrl;

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<?> getAllUsersAndPointsFromISpring(HttpHeaders headers) throws UserNotFoundException {
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<?> responseEntity = restTemplate.exchange(ispringApiUrl + "/gamification/points", HttpMethod.GET, entity, Object.class);
		if(responseEntity == null) {
			throw new UserNotFoundException("Exception in fecthing Users and their points from ISpring API");
		}
		return responseEntity;
	}
	@Override
	public ResponseEntity<?> getUserFromISpring(HttpHeaders headers, String userId) throws UserNotFoundException {
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		Map<String, String> map = new HashMap<>();
		map.put("userId",  userId);
		ResponseEntity<?> responseEntity = restTemplate.exchange(ispringApiUrl + "/user/{userId}", HttpMethod.GET, entity, Object.class, map);
		if(responseEntity == null) {
			throw new UserNotFoundException(String.format("Exception in fecthing UserId : %s from ISpring API", userId));
		}
		return responseEntity;
	}

	
}
