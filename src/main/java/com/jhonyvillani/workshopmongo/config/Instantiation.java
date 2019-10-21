package com.jhonyvillani.workshopmongo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jhonyvillani.workshopmongo.domain.User;
import com.jhonyvillani.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User jhony = new User(null, "Jhony Villani", "jhony@gmail.com");
		User pedro = new User(null, "Pedro Villani", "pedro@gmail.com");
		User jhean = new User(null, "Jhean Villani", "jhean@gmail.com");
		
		userRepository.save(jhony);
		userRepository.save(pedro);
		userRepository.save(jhean);
	}
}
