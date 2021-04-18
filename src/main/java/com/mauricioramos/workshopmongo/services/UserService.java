package com.mauricioramos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauricioramos.workshopmongo.domain.User;
import com.mauricioramos.workshopmongo.dto.UserDTO;
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
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
	
	public User update(User user) {
		User userbd = findById(user.getId());
		updateData(userbd, user);
		return repo.save(userbd);
	}

	private void updateData(User userbd, User user) {
		userbd.setName(user.getName());
		userbd.setEmail(user.getEmail());
		
	}
	
}
