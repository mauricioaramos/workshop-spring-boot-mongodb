package com.mauricioramos.workshopmongo.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mauricioramos.workshopmongo.domain.Post;
import com.mauricioramos.workshopmongo.domain.User;
import com.mauricioramos.workshopmongo.dto.AuthorDTO;
import com.mauricioramos.workshopmongo.dto.CommentDTO;
import com.mauricioramos.workshopmongo.repository.PostRepository;
import com.mauricioramos.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, LocalDate.parse("18/03/2018", formatter), "Partiu viagem", "Fui SP", new AuthorDTO(maria));
		Post post2 = new Post(null, LocalDate.parse("18/03/2018", formatter), "Bom dia", "Acordei", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem man", LocalDate.parse("21/03/2018", formatter), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Se jogue", LocalDate.parse("22/03/2018", formatter), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Fique de boa", LocalDate.parse("23/03/2018", formatter), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}

}
