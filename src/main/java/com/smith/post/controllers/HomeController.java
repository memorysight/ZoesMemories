package com.smith.post.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.smith.post.entities.Post;
import com.smith.post.entities.User;
import com.smith.post.repositories.PostRepository;
import com.smith.post.repositories.UserRepository;


@Controller
public class HomeController {
	
	@Autowired
	PostRepository postRepo;
//	
//	@Autowired
//	CustomerRepository custRepo;
	
	@Autowired
	UserRepository userRepo;

	@GetMapping("/")
//	@GetMapping("localhost://3000")
	public String displayHome(Model model) {
		//this key has to be the same as the key in the home page
		List<Post>posts = postRepo.findAll();
		model.addAttribute("postsList", posts);
		
//		List<User>users = userRepo.findAll();
//		model.addAttribute("usersList", users);
		
//		List<Customer>customers = custRepo.findAll();
//		model.addAttribute("customersList", customers);
		
		List<User>users = userRepo.findAll();
		model.addAttribute("usersList", users);
		
		return "main/home";
//		return "http://localhost/3000";
	}
	
	
}
