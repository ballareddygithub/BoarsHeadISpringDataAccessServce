package com.rewardpoints.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rewardpoints.service.ISpringAccessPointsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ISpringAccessPointsController.class)
public class ISpringAccessPointsControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private ISpringAccessPointsController iSpringrewardPointsController;

	@MockBean
	private ISpringAccessPointsService iSpringAccessPointsService;

	
	private String iSpringBaseURL= "https://api-learn.ispringlearn.com";
	
	@Before
	public void setup() {	
		iSpringrewardPointsController = new ISpringAccessPointsController();
		this.mockMvc = MockMvcBuilders.standaloneSetup(iSpringrewardPointsController).build();				 
		ReflectionTestUtils.setField(iSpringrewardPointsController, "iSpringAccessPointsService", iSpringAccessPointsService);
	}

	@Test
	public void getAllUsersAndPointsFromISpringTest() throws Exception {

		ResponseEntity responseEntity = ResponseEntity.ok("[\r\n" + 
				"  {\r\n" + 
				"    \"userId\": \"2dfc2178-75b2-11ea-9ead-6ace4d08e47c\",\r\n" + 
				"    \"points\": 771\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"userId\": \"74fcf880-75e5-11ea-9eb7-42c5ded08f9d\",\r\n" + 
				"    \"points\": 865\r\n" + 
				"  }\r\n" + 
				"]");
		Mockito.when(iSpringAccessPointsService.getAllUsersAndPointsFromISpring(Mockito.any())).thenReturn(responseEntity);
		mockMvc.perform(MockMvcRequestBuilders
				.get(iSpringBaseURL + "/rewardpoints/gamification/points")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}
	@Test
	public void getUserFromISpringTest() throws Exception {
			ResponseEntity responseEntity = ResponseEntity.ok(getUserJson());
			Mockito.when(iSpringAccessPointsService.getUserFromISpring(Mockito.any(), Mockito.anyString())).thenReturn(responseEntity);
			mockMvc.perform(MockMvcRequestBuilders
					.get(iSpringBaseURL + "/rewardpoints/user/2dfc2178-75b2-11ea-9ead-6ace4d08e47c")
					.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk());
	}
	private String getUserJson() {
		
		return "{\r\n" + 
				"  \"role\": \"administrator\",\r\n" + 
				"  \"roleId\": \"eaf01662-2ae1-11e9-aa24-0242ac13000a\",\r\n" + 
				"  \"userId\": \"2dfc2178-75b2-11ea-9ead-6ace4d08e47c\",\r\n" + 
				"  \"departmentId\": \"71bcd598-c6ba-11ea-918e-82e47ebefe73\",\r\n" + 
				"  \"status\": 1,\r\n" + 
				"  \"fields\": [\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD1\",\r\n" + 
				"      \"value\": \"113743\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"FIRST_NAME\",\r\n" + 
				"      \"value\": \"Ricarda\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"LAST_NAME\",\r\n" + 
				"      \"value\": \"Conely\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"LOGIN\",\r\n" + 
				"      \"value\": \"Ricarda.Conely@boarshead.com\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"EMAIL\",\r\n" + 
				"      \"value\": \"Ricarda.Conely@boarshead.com\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"JOB_TITLE\",\r\n" + 
				"      \"value\": \"Analyst III, Systems\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD12\",\r\n" + 
				"      \"value\": \"Delicatessen Services Co., LLC\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD2\",\r\n" + 
				"      \"value\": \"Sarasota - 200/300 Series\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD9\",\r\n" + 
				"      \"value\": \"915025 Sarasota - Management Information Systems\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD4\",\r\n" + 
				"      \"value\": \"Michelle Nixon\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD13\",\r\n" + 
				"      \"value\": \"Castor\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD11\",\r\n" + 
				"      \"value\": \"\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD5\",\r\n" + 
				"      \"value\": \"\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD10\",\r\n" + 
				"      \"value\": \"\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD3\",\r\n" + 
				"      \"value\": \"\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD8\",\r\n" + 
				"      \"value\": \"\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD6\",\r\n" + 
				"      \"value\": \"Sarasota\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"USER_DEFINED_FIELD7\",\r\n" + 
				"      \"value\": \"FL\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"COUNTRY\",\r\n" + 
				"      \"value\": \"224\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"addedDate\": \"2020-04-03\",\r\n" + 
				"  \"lastLoginDate\": \"2021-11-30\",\r\n" + 
				"  \"groups\": [\r\n" + 
				"    \"c07fd7ac-ee15-11ea-a6c5-868d178ff8b2\",\r\n" + 
				"    \"541dd02e-bd80-11eb-bcd3-12833af9e441\",\r\n" + 
				"    \"39c38d6e-bd66-11eb-9787-b21998aee3c7\",\r\n" + 
				"    \"0c64e63a-bd69-11eb-91a8-e2ba74138d7f\"\r\n" + 
				"  ],\r\n" + 
				"  \"manageableDepartmentIds\": [\r\n" + 
				"    \"2dd1d670-75b2-11ea-b7d9-6ace4d08e47c\"\r\n" + 
				"  ],\r\n" + 
				"  \"userRoles\": [\r\n" + 
				"    {\r\n" + 
				"      \"roleId\": \"eaf01662-2ae1-11e9-aa24-0242ac13000a\",\r\n" + 
				"      \"roleType\": \"administrator\",\r\n" + 
				"      \"manageableDepartmentIds\": [\r\n" + 
				"        \"2dd1d670-75b2-11ea-b7d9-6ace4d08e47c\"\r\n" + 
				"      ]\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"roleId\": \"eaefe76e-2ae1-11e9-b90a-0242ac13000a\",\r\n" + 
				"      \"roleType\": \"owner\",\r\n" + 
				"      \"manageableDepartmentIds\": [\r\n" + 
				"        \"2dd1d670-75b2-11ea-b7d9-6ace4d08e47c\"\r\n" + 
				"      ]\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"roleId\": \"e0317452-d7e4-11ea-841f-0aad442090ab\",\r\n" + 
				"      \"roleType\": \"studio_author\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
	}
}
