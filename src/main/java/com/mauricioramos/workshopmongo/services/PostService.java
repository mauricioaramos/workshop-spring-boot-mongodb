package com.mauricioramos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauricioramos.workshopmongo.domain.Post;
import com.mauricioramos.workshopmongo.domain.User;
import com.mauricioramos.workshopmongo.repository.PostRepository;
import com.mauricioramos.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public List<Post> findAll(){
		return repo.findAll();
	}
	
	public Post findById( String id ) {
		Optional<Post> postOptional = repo.findById(id);
		return postOptional.orElseThrow(() -> new ObjectNotFoundException(id) );
		
	}
	
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}
	
}
