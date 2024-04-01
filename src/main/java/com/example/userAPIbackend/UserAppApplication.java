package com.example.userAPIbackend;

import com.example.userAPIbackend.entity.User;
import com.example.userAPIbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
	public class UserAppApplication implements CommandLineRunner {

	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User[] users = new User[]{
				User.builder().userId(1L).firstName("Agam").lastName("Aloni").email("aa@hfhg.com").
						password("Qq!10000").build(),
		User.builder().userId(2L).firstName("dan").lastName("haschan").email("adda@hfhg.com").
				password("Qq!10000").build()
		};

		for (User user : users) {
			userService.addUser(user);
		}
	}
}