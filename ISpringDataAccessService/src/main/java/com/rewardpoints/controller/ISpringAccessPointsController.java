package com.rewardpoints.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewardpoints.exception.UserNotFoundException;
import com.rewardpoints.service.ISpringAccessPointsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="ISpring Data Access API", description = "Api for ISpring")
@RequestMapping("/rewardpoints")
@CrossOrigin
public class ISpringAccessPointsController {

	@Inject
	private ISpringAccessPointsService iSpringAccessPointsService;
	
	@Operation(summary="getAllUsersAndPointsFromISpring",description="API to fetch a single User")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "all ok"),
			@ApiResponse(responseCode = "400", description = "bad request data"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Permission denied")
	})
	@GetMapping("/gamification/points")
	public ResponseEntity<?> getAllUsersAndPointsFromISpring(@RequestHeader(value = "X-Auth-Account-Url", defaultValue="https://boarshead.ispringlearn.com") String xAuthAccountUrl, 
			@RequestHeader(value = "X-Auth-Email", defaultValue="support@ispring.com") String xAuthEmail, 
			@RequestHeader(value = "X-Auth-Password", defaultValue="iSpring2021!") String xAuthPassword) throws UserNotFoundException {   
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("X-Auth-Account-Url", xAuthAccountUrl);
		headers.add("X-Auth-Email", xAuthEmail);
		headers.add("X-Auth-Password", xAuthPassword);
		ResponseEntity<?> responseEntity = iSpringAccessPointsService.getAllUsersAndPointsFromISpring(headers);
		return responseEntity;
	}
	@Operation(summary="getUserFromISpring",description="API to fetch all users and their points")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "all ok"),
			@ApiResponse(responseCode = "400", description = "bad request data"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Permission denied")
	})
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUserFromISpring(@RequestHeader(value = "X-Auth-Account-Url", defaultValue="https://boarshead.ispringlearn.com") String xAuthAccountUrl, 
			@RequestHeader(value = "X-Auth-Email", defaultValue="support@ispring.com") String xAuthEmail, 
			@RequestHeader(value = "X-Auth-Password", defaultValue="iSpring2021!") String xAuthPassword, @PathVariable("userId") String userId) throws UserNotFoundException {   
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("X-Auth-Account-Url", xAuthAccountUrl);
		headers.add("X-Auth-Email", xAuthEmail);
		headers.add("X-Auth-Password", xAuthPassword);
		ResponseEntity<?> responseEntity = iSpringAccessPointsService.getUserFromISpring(headers, userId);
		return responseEntity;
	}
}
