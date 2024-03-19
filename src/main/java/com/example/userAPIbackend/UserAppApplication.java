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

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

	UserService userService;

	@Override
	public void run(String... args) throws Exception {
		User[] users = new User[]{
				new User("Agam", "Aloni", "aa@gmaid.com", "#DRFfdfdf123GH%"),
				new User("Dan", "Yom", "dd@gmaid.com", "#DRFG32fdsff3H%")
		};

		for (User user : users) {
			userService.addUser(user);
		}
	}
}