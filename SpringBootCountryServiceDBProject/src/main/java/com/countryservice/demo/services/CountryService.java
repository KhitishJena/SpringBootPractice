package com.countryservice.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.AddResponse;

@Component   //used for injecting dependency annotation.

public class CountryService {
	
	static HashMap<Integer, Country> countryIdMap;
	
	public CountryService() 
	{
		countryIdMap = new HashMap<Integer, Country>();
		
		Country indiaCountry = new Country(1, "India", "New Delhi");
		Country usaCountry = new Country(2, "USA", "New York");
		Country ukCountry = new Country(3, "UK", "London");
		
		countryIdMap.put(1, indiaCountry);
		countryIdMap.put(2, usaCountry);
		countryIdMap.put(3, ukCountry);
	}
	
	public List getAllCountries() {
		
		List countries = new ArrayList(countryIdMap.values());
		return countries;
	}

	public Country getCountryById(int id) {
		
		Country country = countryIdMap.get(id);
		return country;
	}
	
	public Country getCountryByName(String countryName) {
		
		Country country = null;
		for(int i:countryIdMap.keySet())
		{
			if(countryIdMap.get(i).getCountryName().equalsIgnoreCase(countryName))
				country = countryIdMap.get(i);
		}
		return country; 
	}
	
	public Country addCountry(Country country) {
		
		country.setId(getMaxId());
		countryIdMap.put(country.getId(), country);
		return country;
		
	}
	
	// Utility to get the maximum id in the HashTable
	public static int getMaxId() {
		int max =0;
		for (int id: countryIdMap.keySet())
			if(max<=id)
				max=id;
		return max+1;
	}
	
	public Country updateCountry(Country country) {
		
		if(country.getId()>0)
			countryIdMap.put(country.getId(), country);
		return country;
			
	}
	
	public AddResponse deleteCountry(int id) {
		
		countryIdMap.remove(id);
		AddResponse response = new AddResponse();
		response.setMsg("^^^^^^^^^^^^Country id Deleted*************");
		response.setId(id);
		return response;
	}
}
