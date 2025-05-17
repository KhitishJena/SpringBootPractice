package com.countryservice.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public List<Country> getCountries() {

		// postman --> http://localhost:8080/getcountries
		return countryservice.getAllCountries();
	}

	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable int id) {

		// postman--> http://localhost:8080/getcountries/2
		try {
			Country country = countryservice.getCountryById(id);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName) {

		// postman--> http://localhost:8080/getcountries/countryname?name=india
		try {
			Country country = countryservice.getCountryByName(countryName);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}

	}

	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {

		// postman--> http://localhost:8080/addcountry
		// input body--> { "countryName" : "Italyo", "countryCapital" : "Romeo" }

		return countryservice.addCountry(country);
	}

	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int id, @RequestBody Country country) {
		
		//postman ---> http://localhost:8080/updatecountry
		//inputbody ----> {"id" : 4, "countryName" : "Italyo", "countryCapital" : "Romeo0" }
		try {
		Country existCountry = countryservice.getCountryById(id);
		existCountry.setCountryName(country.getCountryName());
		existCountry.setCountryCapital(country.getCountryCapital());
		
		Country updatedCountry = countryservice.updateCountry(existCountry);
		return new ResponseEntity<Country>(updatedCountry, HttpStatus.OK);
		
		}
		
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
	}

	@DeleteMapping("/deletecountry/{id}")
	public AddResponse deleteCountry(@PathVariable int id) {

		// postman ----> http://localhost:8080/deletecountry/4

		return countryservice.deleteCountry(id);
	}

}
