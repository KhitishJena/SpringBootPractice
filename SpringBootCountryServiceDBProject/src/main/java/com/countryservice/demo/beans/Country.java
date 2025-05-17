package com.countryservice.demo.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity                     //this annotation helps in representing the bean class as the database table
@Table(name="samplecountry")      //this specifies the table that needs to be accessed in the db
public class Country {
	
	@Id                     //this annotation represents this column in db is a primary key
	@Column(name = "id")    //this annotation maps the variables of the bean class to the database corresponding table columns
	int id;
	
	@Column(name = "countryname")  //this annotation maps the variables of the bean class to the database corresponding table columns. 
	String countryName;			   //donot give the names of the columns in database in Camelcase. it causes column not found error
	
	@Column(name = "countrycapital")  //this annotation maps the variables of the bean class to the database corresponding table columns
	String countryCapital;
	
	// Default constructor
	public Country() {
		
	}
	
	
	//Parametrized constructor
	public Country(int id, String countryName, String countryCapital) {
		this.id = id;
		this.countryName= countryName;
		this.countryCapital= countryCapital;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCapital() {
		return countryCapital;
	}
	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}

}
