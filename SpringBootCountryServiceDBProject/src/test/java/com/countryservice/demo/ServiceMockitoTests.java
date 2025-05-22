package com.countryservice.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.repositories.CountryRepository;
import com.countryservice.demo.services.CountryService;

@SpringBootTest(classes = {ServiceMockitoTests.class})				//This annotation marks this class as Springboot test class
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)				//This annotation helps in specifying that test methods needs to be executed in orders mentioned

public class ServiceMockitoTests {

	@Mock															//This annotation marks which class we are going to mock
	CountryRepository countryrep;
	
	@InjectMocks													//This annotation helps in calling the service class methods
	CountryService countryService;
	
	//List<Country> mycountries;
	
	@Test															//This marks the method as Test method
	@Order(1)														//This annotation specifies the order of executions of tests
	public void test_getAllCountries() {
		
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "NewDelhi"));
		mycountries.add(new Country(2, "USA", "NewYork"));
		mycountries.add(new Country(3, "Japan", "Tokyo"));
		
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
		mycountries.add(new Country(2, "USA", "NewYork"));
		mycountries.add(new Country(3, "Japan", "Tokyo"));
		
		int countryID = 2;
		
		when(countryrep.findAll()).thenReturn(mycountries);
		int actualCountryID = countryService.getCountryById(countryID).getId();
		assertEquals(countryID, actualCountryID);
		
	}
	
	@Test															//This marks the method as Test method
	@Order(3)														//This annotation specifies the order of executions of tests
	public void test_getCountryByName() {
		
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "NewDelhi"));
		mycountries.add(new Country(2, "USA", "NewYork"));
		mycountries.add(new Country(3, "Japan", "Tokyo"));
		
		String countryName = "USA";
		
		when(countryrep.findAll()).thenReturn(mycountries);
		String actualCountryName = countryService.getCountryByName(countryName).getCountryName();
		assertEquals(countryName, actualCountryName);
		
	}
	
	@Test															//This marks the method as Test method
	@Order(4)														//This annotation specifies the order of executions of tests
	public void test_addCountry() {
		
		Country country = new Country(4, "Germany", "Berlin");
		
		when(countryrep.save(country)).thenReturn(country);
		Country actualCountry = countryService.addCountry(country);
		assertEquals(country, actualCountry);
		
	}
	
	@Test															//This marks the method as Test method
	@Order(5)														//This annotation specifies the order of executions of tests
	public void test_updateCountry() {
		
		Country country = new Country(4, "Germany", "Berlin");
		
		when(countryrep.save(country)).thenReturn(country);
		Country actualCountry = countryService.updateCountry(country);
		assertEquals(country, actualCountry);
		
	}
	
	@Test															//This marks the method as Test method
	@Order(6)														//This annotation specifies the order of executions of tests
	public void test_deleteCountry() {
		
		Country country = new Country(4, "Germany", "Berlin");
		
		when(countryrep.save(country)).thenReturn(country);
		
		int id = country.getId();
		countryService.deleteCountry(id);
		
		Country countryActual = countryService.getCountryById(id);
		assertNull(countryActual);
		
		/*
		 * for mocking void methods:
		 * verify(repositoryObjectname, times(1)).serviceMethod;
		*/
	}

}














