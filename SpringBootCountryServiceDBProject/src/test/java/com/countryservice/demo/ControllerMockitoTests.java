package com.countryservice.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.services.CountryService;

@SpringBootTest(classes = {ControllerMockitoTests.class})				//This annotation marks this class as Springboot test class
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)					//This annotation helps in specifying that test methods needs to be executed in orders mentioned


public class ControllerMockitoTests {

	@Mock
	CountryService countryservice;
	
	@InjectMocks
	CountryController countrycontroller;
	
	List<Country> mycountries;
	Country country;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		
		mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "NewDelhi"));
		mycountries.add(new Country(2, "USA", "NewYork"));
		mycountries.add(new Country(3, "Japan", "Tokyo"));
		
		when(countryservice.getAllCountries()).thenReturn(mycountries); 
		ResponseEntity<List<Country>> res = countrycontroller.getCountries();
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		//assertEquals(2, res.getBody().size());                          // For deliberate failing the Tests
		assertEquals(mycountries.size(), res.getBody().size());
		
	}
}
