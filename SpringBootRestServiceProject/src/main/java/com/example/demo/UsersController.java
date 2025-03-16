package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



// LOCAL URI: http://localhost:8080/users/
//query paramter URI:--> http://localhost:8080/users?page=3&limit=100


@RestController  				//
@RequestMapping("/users")  		//this is for stating the resource path of the URI 
public class UsersController {
	
	@GetMapping
	public String getUsers(@RequestParam(value ="page") int pageno, @RequestParam(value ="limit") int limitno) {
		 
		return "~~~~~~~~~~~http GET request was sent for page- "+pageno+" and limit of the page is: "+limitno+"**********";
	}
	
	@GetMapping(path="/{UserId}")    //path parameter
	public String getUser(@PathVariable String UserId) {
		
		return UserId +" ~~~~~~~~~~~http GET user request was sent**********";
	}
	
	@PostMapping
	public String postUsers() {
		
		return "~~~~~~~~~~~http POST request was sent**********";
	}
	
	@PutMapping
	public String updateUsers() {
		
		return "~~~~~~~~~~~http PUT request was sent**********";
	}
	
	@DeleteMapping
	public String deleteUsers() {
		
		return "~~~~~~~~~~~http DELETE request was sent**********";
	}

}
