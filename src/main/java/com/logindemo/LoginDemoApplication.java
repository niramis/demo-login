package com.logindemo;

import com.logindemo.models.ERole;
import com.logindemo.models.Role;
import com.logindemo.models.User;
import com.logindemo.repository.RoleRepository;
import com.logindemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class LoginDemoApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository role_repository;

	@Autowired
	private UserRepository user_repository;

	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(LoginDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		role_repository.deleteAll();
		user_repository.deleteAll();
		role_repository.save(new Role(ERole.ROLE_USER));
	}

}
