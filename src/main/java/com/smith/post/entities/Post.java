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
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long postId;
	private String title;
	private String date;
	private String body;
	private String image;
	

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	private String confidence;
	public float Money;
	
	

	public float getMoney() {
		return Money;
	}

	public void setMoney(float money) {
		Money = money;
	}

	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.DETACH, 
			CascadeType.MERGE, 
			CascadeType.REFRESH, 
			CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(name="user_post",
	joinColumns=@JoinColumn(name="post_id"),
	inverseJoinColumns=@JoinColumn(name="user_id"))
	private List<User> users;
	

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Post(String title, String date, String body) {
		super();
		this.title = title;
		this.date = date;
		this.body = body;
	}
	
	public Post(Long postId, String title, String date, String body, String confidence, List<User> users) {
		super();
		this.postId = postId;
		this.title = title;
		this.date = date;
		this.body = body;
		this.confidence = confidence;
		this.users = users;
	}
	
	
	
	public Post(String title, String date, String body, String image, String confidence, List<User> users) {
		super();
		this.title = title;
		this.date = date;
		this.body = body;
		this.image = image;
		this.confidence = confidence;
		this.users = users;
	}
	
	
	
	

	public Post(String title, String date, String body, String image, String confidence, float money,
			List<User> users) {
		super();
		this.title = title;
		this.date = date;
		this.body = body;
		this.image = image;
		this.confidence = confidence;
		Money = money;
		this.users = users;
	}

	public Post() {
		
	}
	
}