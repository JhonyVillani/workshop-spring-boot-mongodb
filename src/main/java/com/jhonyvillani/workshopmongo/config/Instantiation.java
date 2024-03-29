package com.jhonyvillani.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jhonyvillani.workshopmongo.domain.Post;
import com.jhonyvillani.workshopmongo.domain.User;
import com.jhonyvillani.workshopmongo.dto.AuthorDTO;
import com.jhonyvillani.workshopmongo.dto.CommentDTO;
import com.jhonyvillani.workshopmongo.repository.PostRepository;
import com.jhonyvillani.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User jhony = new User(null, "Jhony Villani", "jhony@gmail.com");
		User pedro = new User(null, "Pedro Villani", "pedro@gmail.com");
		User jhean = new User(null, "Jhean Villani", "jhean@gmail.com");
		
		userRepository.saveAll(Arrays.asList(jhony, pedro, jhean));
		
		Post post1 = new Post(null, sdf.parse("21/10/2019"), "Em viagem", "A caminho de São Paulo!", new AuthorDTO(jhony));
		Post post2 = new Post(null, sdf.parse("23/10/2019"), "Bom dia", "Acordei animado!", new AuthorDTO(jhony));
		
		CommentDTO c1 = new CommentDTO("Boa viagem pra você!", sdf.parse("21/10/2019"), new AuthorDTO(jhean));
		CommentDTO c2 = new CommentDTO("Já era hora", sdf.parse("22/10/2019"), new AuthorDTO(pedro));
		CommentDTO c3 = new CommentDTO("Bom dia", sdf.parse("23/10/2019"), new AuthorDTO(jhean));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		jhony.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(jhony);
	}
}
