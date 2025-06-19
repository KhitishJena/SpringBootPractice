package com.countryservice.demo;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;


@SpringBootTest(classes = {ControllerMockitoTests.class})				//This annotation marks this class as Springboot test class
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)					//This annotation helps in specifying that test methods needs to be executed in orders mentioned

public class ControllerIntegrationTests {

	@Test
	@Order(1)
	public void test_getAllCountriesIntegrationTest() throws JSONException, URISyntaxException {
		
		String expectedResponse = "[\r\n"
				+ "    {\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"countryName\": \"India\",\r\n"
				+ "        \"countryCapital\": \"Delhi\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 2,\r\n"
				+ "        \"countryName\": \"USA\",\r\n"
				+ "        \"countryCapital\": \"NewYork\"\r\n"
				+ "    }\r\n"
				+ "]";
		
		URI url = new URI("http://localhost:8080/getcountries");
		//URI url = new URI("https://www.google.com/");
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		JSONAssert.assertEquals(expectedResponse, response.getBody(), true);
	}
	
}
