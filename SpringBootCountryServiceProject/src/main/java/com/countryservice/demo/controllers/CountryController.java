package com.countryservice.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.services.CountryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController // it helps Springboot understands this is a rest controller class. all request
				// hit this controller class.
public class CountryController {

	@Autowired // in spring boot there is no need to create object of the class. one can create
				// variables of that class using autowired annotation.
	CountryService countryservice;

	@GetMapping("/getcountries")
	public List getCountries() {

		//postman --> http://localhost:8080/getcountries
		return countryservice.getAllCountries();
	}

	@GetMapping("/getcountries/{id}")
	public Country getCountryById(@PathVariable int id) {

		//postman--> http://localhost:8080/getcountries/2
		return countryservice.getCountryById(id);
	}

	@GetMapping("/getcountries/countryname")
	public Country getCountryByName(@RequestParam(value = "name") String countryName) {
		
		//postman--> http://localhost:8080/getcountries/countryname?name=india

		return countryservice.getCountryByName(countryName);
	}

	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {

		return countryservice.addCountry(country);
	}

	@PutMapping("/updatecountry")
	public Country updateCountry(@RequestBody Country country) {

		return countryservice.updateCountry(country);
	}
	
	@DeleteMapping("/deletecountry/{id}")
	public AddResponse deleteCountry(@PathVariable int id) {
		
		return countryservice.deleteCountry(id);
	}
	

}
