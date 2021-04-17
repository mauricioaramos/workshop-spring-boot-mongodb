package com.mauricioramos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauricioramos.workshopmongo.domain.User;
import com.mauricioramos.workshopmongo.repository.UserRepository;
import com.mauricioramos.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById( String id ) {
		Optional<User> userOptional = repo.findById(id);
		return userOptional.orElseThrow(() -> new ObjectNotFoundException(id) );
		
	}
}
