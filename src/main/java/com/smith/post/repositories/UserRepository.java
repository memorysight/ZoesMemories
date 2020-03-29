package com.smith.post.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.smith.post.entities.User;



public interface UserRepository extends CrudRepository <User, Long>{
	
	@Override
	public List<User> findAll();
	
	Optional<User> findByUserId(Long userId);
	
	
//	Optional<Customer> getCustomerById(Long customerId);
	
//	Customer getCustomerById(Long Id);

}