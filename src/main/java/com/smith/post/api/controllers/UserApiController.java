package com.smith.post.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smith.post.entities.User;
import com.smith.post.repositories.UserRepository;



@RestController
@RequestMapping("/app-api/users")
public class UserApiController {
	
	@Autowired
	UserRepository userRepo;
	

	@CrossOrigin("http://localhost:3000")
	@GetMapping
	public Iterable<User> getUsers(){
		return userRepo.findAll();
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") Long id) {
		return userRepo.findById(id).get();
		
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		System.out.println("Posts " + user.getPosts()); 
		return userRepo.save(user);
	}
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping(path="/{userId}",consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public User update(@RequestBody User user) {
		return userRepo.save(user);
	}
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("userId") Long id) {
		try {
		userRepo.deleteById(id);
	}catch(EmptyResultDataAccessException e){
		
	}

	}
	
	
}

