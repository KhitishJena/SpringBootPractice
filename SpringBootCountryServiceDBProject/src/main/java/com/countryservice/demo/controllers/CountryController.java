package com.countryservice.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;

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
	public ResponseEntity<List<Country>> getCountries() {

		// postman --> http://localhost:8080/getcountries
		try {
			List<Country> countries = countryservice.getAllCountries();
			return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable int id) {

		// postman--> http://localhost:8080/getcountries/2
		try {
			Country country = countryservice.getCountryById(id);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName) {

		// postman--> http://localhost:8080/getcountries/countryname?name=india
		/*
		try {
			Country country = countryservice.getCountryByName(countryName);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
			
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		*/
		Country country = countryservice.getCountryByName(countryName);
		if(country!=null) {
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/addcountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {

		// postman--> http://localhost:8080/addcountry
		// input body--> { "countryName" : "Italyo", "countryCapital" : "Romeo" }
		try {
			 country =  countryservice.addCountry(country);
			 return new ResponseEntity<Country>(country, HttpStatus.CREATED);
		}
		catch (Exception e){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
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
	public ResponseEntity<Country> deleteCountry(@PathVariable("id") int id) {

		// postman ----> http://localhost:8080/deletecountry/4
		Country country = null;
		try {
			country =  countryservice.getCountryById(id);
			countryservice.deleteCountry(id);	
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}

}
