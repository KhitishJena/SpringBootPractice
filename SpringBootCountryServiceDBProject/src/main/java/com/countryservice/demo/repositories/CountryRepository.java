package com.countryservice.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countryservice.demo.beans.Country;

//JPA repository class helps in getting data from the Database

public interface CountryRepository extends JpaRepository<Country, Integer>{

	/*
	JPARepository took two inputs that is the bean classname to know what database table it has
	to operate on and the datatype of the primary key in that table
	*/
	
}
