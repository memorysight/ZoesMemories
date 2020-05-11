package com.smith.post.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("update", false);
		model.addAttribute("post", aPost);
		return "posts/new-post";
		
	}
	
	@GetMapping("/update/{id}")
	public String getById(Model model, @PathVariable("id") Long id) {
		
		List<User> users = userRepo.findAll();
		model.addAttribute("allUsers",users);
		model.addAttribute("update",true);
		Optional<Post>aPost = postRepo.findById(id);
	
		if(aPost.isPresent()) {
			model.addAttribute("post", aPost);
			return "posts/new-post";
		}else {
			return "error";
		}
	}
	
	@PostMapping("/update")
	public String updateById(Post post) {
		Optional<Post>aPost = postRepo.findById(post.getPostId());
		
		if(aPost.isPresent()) {
			Post existingPost = aPost.get();
			existingPost.setBody(post.getBody());
			existingPost.setConfidence(post.getConfidence());
			existingPost.setDate(post.getDate());
			existingPost.setImage(post.getImage());
			existingPost.setMoney(post.getMoney());
			existingPost.setPostId(post.getPostId());
			existingPost.setTitle(post.getTitle());
			existingPost.setUsers(post.getUsers());

			postRepo.save(aPost.get());
			
			return "redirect:http://localhost:3000/posts";
		}else {
			return "error";
		}
		
		
	}
	
	@PostMapping("/save")
	public String createPostForm(Post post, Model model) {

		postRepo.save(post);
//		return "redirect:/posts/new"; redirects back to thymeleaf
		return "redirect:http://localhost:3000/posts";
//		return "redirect:/posts";
		
	}
	

}

