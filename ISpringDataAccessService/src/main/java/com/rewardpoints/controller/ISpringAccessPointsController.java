package com.rewardpoints.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="ISpring Data Access API", description = "Api for ISpring")
@RequestMapping("/rewardpoints")
@CrossOrigin
public class ISpringAccessPointsController {
	
	@Value("${ispring.api.url}")
	private String ispringApiUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
    @Operation(summary="getAllUsersAndPointsFromISpring",description="API to fetch a single User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all ok"),
            @ApiResponse(responseCode = "400", description = "bad request data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Permission denied")
    })
    @GetMapping("/gamification/points")
    public ResponseEntity<?> getAllUsersAndPointsFromISpring() {   
    	
    	ResponseEntity<?> responseEntity = restTemplate.getForEntity( ispringApiUrl + "/gamification/points", List.class);
    	if(responseEntity != null) {
    		responseEntity.getBody();
    	}
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
    public ResponseEntity<?> getUserFromISpring() {   
    	
    	ResponseEntity<?> responseEntity = restTemplate.getForEntity( ispringApiUrl + "/user/points", List.class);
    	if(responseEntity != null) {
    		responseEntity.getBody();
    	}
    	return responseEntity;
    }
    
    @GetMapping("/ping")
    public String ping() {
    	
    	return "pong";
    }
}
