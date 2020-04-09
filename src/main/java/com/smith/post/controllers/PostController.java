package com.smith.post.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smith.post.entities.Post;
import com.smith.post.entities.User;
import com.smith.post.repositories.PostRepository;
import com.smith.post.repositories.UserRepository;


@Controller
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	UserRepository userRepo;
	
	//this is new to the base controller to add list-users table page
	@GetMapping
	public String displayPosts(Model model) {
	List<Post>posts = postRepo.findAll();
	model.addAttribute("posts", posts);
	return "posts/list-posts";
	}
	
	//base controller before list-events is implemented
	@GetMapping("/new")
	public String displayPostForm(Model model) {
		Post aPost = new Post();
		
		List<User> users = userRepo.findAll();  //new
		model.addAttribute("allUsers", users);
		
		
		model.addAttribute("post", aPost);
		return "posts/new-post";
		
	}
	
	@PostMapping("/save")
	public String createPostForm(Post post, Model model) {
		//this should handle the saving to the database
		
		//use to prevent duplicate submissions
		postRepo.save(post);
//		return "redirect:/posts/new"; redirects back to thymeleaf
		return "redirect:http://localhost:3000/posts";
		
	}
	

}

