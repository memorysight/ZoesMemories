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

import com.smith.post.entities.Post;
import com.smith.post.repositories.PostRepository;


@RestController
@RequestMapping("/app-api/posts")
public class PostApiController {
	
	@Autowired
	PostRepository postRepo;
	
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping
	public Iterable<Post> getPosts(){
		return postRepo.findAll();
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/{postId}")
	public Post getPostById(@PathVariable("postId") Long id) {
		return postRepo.findById(id).get();
		
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Post create(@RequestBody Post post) {
		return postRepo.save(post);
	}
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping(path="/{postId}",consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Post update(@RequestBody Post post) {
		return postRepo.save(post);
	}
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/{postId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("postId") Long id) {
		try {
		postRepo.deleteById(id);
	}catch(EmptyResultDataAccessException e){
		
	}

	}
	
	
}

