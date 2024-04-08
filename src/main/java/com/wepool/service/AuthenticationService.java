package com.wepool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wepool.model.AuthenticationRequest;
import com.wepool.model.AuthenticationReponse;
import com.wepool.model.RegisterRequest;
import com.wepool.model.User;
import com.wepool.repository.UserRepository;

@Service
public class AuthenticationService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationReponse register(RegisterRequest request) {
		var user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		System.out.println("pass"+request.getPassword());
		System.out.println("encpass"+passwordEncoder.encode(request.getPassword()));
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		System.out.println("uspass"+user.getPassword());
		user.setAuthorities("admin");
		repository.save(user);
		var token=jwtService.generateToken(user);
		return new AuthenticationReponse(token);
	}

	public AuthenticationReponse authentication(AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		var  user=repository.findByEmail(request.getEmail()).orElseThrow();
		String token=jwtService.generateToken(user);
		return new AuthenticationReponse(token);
	}

}
