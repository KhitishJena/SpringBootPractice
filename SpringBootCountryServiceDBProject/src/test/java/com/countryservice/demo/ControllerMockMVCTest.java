package com.countryservice.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.services.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = {ControllerMockitoTests.class})				//This annotation marks this class as Springboot test class
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)					//This annotation helps in specifying that test methods needs to be executed in orders mentioned
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.restservices.demo")


public class ControllerMockMVCTest {
	
	@Autowired
	MockMvc mockMvc;

	@Mock
	CountryService countryService;
	
	@InjectMocks 
	CountryController countryController;
	
	List<Country> mycountries;
	Country country;
	
	@BeforeEach															//Executes before each TestCase.
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
	}
	
	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception {
		
		mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "NewDelhi"));
		mycountries.add(new Country(2, "USA", "NewYork"));
		mycountries.add(new Country(3, "Japan", "Tokyo"));
		
		when(countryService.getAllCountries()).
		thenReturn(mycountries);
		
		this.mockMvc.perform(get("/getcountries")).
		andExpect(status().isFound()).andDo(print());
		
	}
	
	@Test
	@Order(2)
	public void test_getCountryBYId() throws Exception {
	
		country = new Country(2, "USA", "NewYork");
		int countryID=2;
		
		when(countryService.getCountryById(countryID)).thenReturn(country);
		
		this.mockMvc.perform(get("/getcountries/{id}",countryID)).
		andExpect(status().isFound()).
		andExpect(MockMvcResultMatchers.jsonPath(".id").value(2)).
		andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA")).
		andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("NewYork")).
		andDo(print());
	}
	
	@Test
	@Order(3)
	public void test_getCountryBYName() throws Exception {
	
		country = new Country(2, "USA", "NewYork");
		String countryName = "USA";
		
		when(countryService.getCountryByName(countryName)).thenReturn(country);
		
		this.mockMvc.perform(get("/getcountries/countryname").param("name","USA")).
		andExpect(status().isFound()).
		andExpect(MockMvcResultMatchers.jsonPath(".id").value(2)).
		andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA")).
		andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("NewYork")).
		andDo(print());
	}
	
	
	@Test
	@Order(4)
	public void test_addCountry() throws Exception {
	
		country = new Country(4, "Germany", "Berlin");
	
		when(countryService.addCountry(country)).thenReturn(country);
		
		ObjectMapper mapper = new ObjectMapper();
		String inputJsonBody = mapper.writeValueAsString(country);
		
		this.mockMvc.perform(post("/addcountry")
				.content(inputJsonBody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andDo(print());		
	}
	
	@Test
	@Order(5)
	public void test_updateCountry() throws Exception {
		
		country = new Country(4, "Japan", "Sanghai");
		int countryID=3;
	
		when(countryService.getCountryById(countryID)).thenReturn(country);
		when(countryService.updateCountry(country)).thenReturn(country);
		
		ObjectMapper mapper = new ObjectMapper();
		String inputJsonBody = mapper.writeValueAsString(country);
		
		this.mockMvc.perform(put("/updatecountry/{id}",countryID)
				.content(inputJsonBody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("Japan"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Sanghai"))
		.andDo(print());
		
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() throws Exception {
		
		country = new Country(4, "Japan", "Sanghai");
		int countryID=4;
		
		when(countryService.getCountryById(countryID)).thenReturn(country);
		
		this.mockMvc.perform(delete("/deletecountry/{id}",countryID))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
}

