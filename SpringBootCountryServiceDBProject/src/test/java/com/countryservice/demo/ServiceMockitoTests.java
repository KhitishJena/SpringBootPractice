package com.countryservice.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.repositories.CountryRepository;
import com.countryservice.demo.services.CountryService;

@SpringBootTest(classes = {ServiceMockitoTests.class})				//This annotation marks this class as Springboot test class
public class ServiceMockitoTests {

	@Mock															//This annotation marks which class we are going to mock
	CountryRepository countryrep;
	
	@InjectMocks													//This annotation helps in calling the service class methods
	CountryService countryService;
	
	List<Country> mycountries;
	
	@Test															//This marks the method as Test method
	@Order(1)														//This annotation specifies the order of executions of tests
	public void test_getAllCountries() {
		
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "NewDelhi"));
		mycountries.add(new Country(1, "USA", "NewYork"));
		mycountries.add(new Country(1, "Japan", "Tokyo"));
		
		when(countryrep.findAll()).thenReturn(mycountries);			//This is the mocking statement
		int expected = countryService.getAllCountries().size();
		int actual = mycountries.size();
		assertEquals(expected, actual);
	}
	
	@Test															//This marks the method as Test method
	@Order(2)														//This annotation specifies the order of executions of tests
	public void test_getCountryById() {
		
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "NewDelhi"));
		mycountries.add(new Country(1, "USA", "NewYork"));
		mycountries.add(new Country(1, "Japan", "Tokyo"));
		
	}
}














