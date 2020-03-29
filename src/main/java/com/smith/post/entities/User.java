package com.smith.post.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private String image;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.DETACH, 
			CascadeType.MERGE, 
			CascadeType.REFRESH, 
			CascadeType.PERSIST}, fetch = FetchType.LAZY)
			@JoinTable(name="user_post",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="post_id"))
	private List<Post>Posts;
	
	

	public List<Post> getPosts() {
		return Posts;
	}

	public void setPosts(List<Post> posts) {
		Posts = posts;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	

	public User(String firstName, String lastName, String password, String role, String image) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
		this.image = image;
	}
	
	
	
	public User(String firstName, String lastName, String email, String password, String role, String image,
			List<Post> posts) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.image = image;
		Posts = posts;
	}

	public User() {
		
	}
}
