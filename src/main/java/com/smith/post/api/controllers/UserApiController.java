package com.smith.post.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.smith.post.entities.Post;
import com.smith.post.entities.User;
import com.smith.post.repositories.PostRepository;
import com.smith.post.repositories.UserRepository;



@RestController
@RequestMapping("/app-api/users")
public class UserApiController {
	
	@Autowired
	UserRepository userRepo;
	

	@Autowired
	PostRepository postRepo;
	
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
		List<Post> uiPosts = user.getPosts(); 
		List<Post> dbPosts = new ArrayList<>(); 
		for (Post post : uiPosts) {
			Optional<Post>foundPost = postRepo.findById(post.getPostId());
			if(foundPost.isPresent()) {
				dbPosts.add(foundPost.get());
			}
		}
		user.setPosts(dbPosts);
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

