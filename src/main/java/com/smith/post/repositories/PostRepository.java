package com.smith.post.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.smith.post.entities.Post;




public interface PostRepository extends CrudRepository <Post, Long>{


	
	@Override
	public List<Post> findAll();
	
	Optional<Post> findByPostId(Long postId);
	
	
}