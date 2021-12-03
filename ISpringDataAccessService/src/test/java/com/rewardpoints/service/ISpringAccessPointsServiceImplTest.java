package com.rewardpoints.service;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rewardpoints.exception.UserNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ISpringAccessPointsServiceImplTest {

	@InjectMocks
	private ISpringAccessPointsServiceImpl iSpringAccessPointsServiceImpl;
	
	@Mock
	private RestTemplate restTemplate;
	
	HttpHeaders headers = new HttpHeaders();
	
	@Before	
	public void setUp() {
		MockitoAnnotations.openMocks(this);	
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("X-Auth-Account-Url", "https://boarshead.ispringlearn.com");
		headers.add("X-Auth-Email", "support@ispring.com");
		headers.add("X-Auth-Password", "iSpring2021!");
	}
	@Test
	public void getAllUsersAndPointsFromISpringTest() throws UserNotFoundException {
		ResponseEntity responseEntity = ResponseEntity.ok("{}");
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(), Mockito.eq(Object.class))).thenReturn(responseEntity);
		ResponseEntity<?> response = iSpringAccessPointsServiceImpl.getAllUsersAndPointsFromISpring(headers);
		assertNotNull(response);
	}
	@Test(expected = UserNotFoundException.class)
	public void getAllUsersAndPointsFromISpringTestForEx() throws UserNotFoundException {
		ResponseEntity responseEntity = ResponseEntity.ok("{}");
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(), Mockito.eq(Object.class))).thenReturn(null);
		iSpringAccessPointsServiceImpl.getAllUsersAndPointsFromISpring(headers);
	}
	@Test
	public void getUserFromISpringTest() throws UserNotFoundException {
		ResponseEntity responseEntity = ResponseEntity.ok("[{}]");
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(), Mockito.eq(Object.class), Mockito.anyMap())).thenReturn(responseEntity);
		ResponseEntity<?> response = iSpringAccessPointsServiceImpl.getUserFromISpring(headers, "2dfc2178-75b2-11ea-9ead-6ace4d08e47c");
		assertNotNull(response);
	}
	@Test(expected = UserNotFoundException.class)
	public void getUserFromISpringTestForException() throws UserNotFoundException {
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(), Mockito.eq(Object.class), Mockito.anyMap())).thenReturn(null);
		iSpringAccessPointsServiceImpl.getUserFromISpring(headers, "2dfc2178-75b2-11ea-9ead-6ace4d08e47c");
		
	}
}
