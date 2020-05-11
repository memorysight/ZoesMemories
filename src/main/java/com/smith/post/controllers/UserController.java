package com.smith.post.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smith.post.entities.Post;
import com.smith.post.entities.User;
import com.smith.post.repositories.PostRepository;
import com.smith.post.repositories.UserRepository;


@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PostRepository postRepo;
	

	//this is new to the base controller to add list-users table page
	@GetMapping
	public String displayUsers(Model model) {
	List<User>users = userRepo.findAll();
	model.addAttribute("users", users);
	return "users/list-users";
	}
	
	//displays the new customer form and thus this will be updated with the new method to get events
	@GetMapping("/new")
	public String displayUserForm(Model model) {
		User aUser = new User();
		//aUser.setUserId(-1L); 
		List<Post> posts = postRepo.findAll();  //new
		model.addAttribute("allPosts", posts);  //new
		model.addAttribute("update", false);  //new
		model.addAttribute("user", aUser);
		return "users/new-user";

	}
	
	@GetMapping("/update/{id}")
	public String getById(Model model, @PathVariable("id") Long id) {
		
		List<Post> posts = postRepo.findAll();  //new
		model.addAttribute("allPosts", posts);  //
		model.addAttribute("update", true);  //new
		Optional<User> aUser = userRepo.findById(id); 
		if (aUser.isPresent()) {
			model.addAttribute("user", aUser);
			return "users/new-user";
		}else {
			// page not found 404 
			return "error";
		}
		
		

	}
	
	@PostMapping("/update/")
	public String updateById(User user) {
		userRepo.save(user);
		return "redirect:/users";

	}
	
	
	@PostMapping("/save")
	public String createUser(User user, Model model) {
		userRepo.save(user);
		return "redirect:/users";
	
	}
	

	

	
	
}
