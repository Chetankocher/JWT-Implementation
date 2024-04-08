package com.wepool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wepool.model.AuthenticationRequest;
import com.wepool.model.AuthenticationReponse;
import com.wepool.model.RegisterRequest;
import com.wepool.service.AuthenticationService;

@Controller
@RequestMapping("/auth")
public class AuthenticatoionController {
	
	@Autowired
	public AuthenticationService service;
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<AuthenticationReponse> register( @RequestBody RegisterRequest request)
	{
		
		return ResponseEntity.ok(service.register(request));
	}
	
	@PostMapping("/authenticate")
	@ResponseBody
	public ResponseEntity<AuthenticationReponse> authentication( @RequestBody AuthenticationRequest request)
	{
		
		return ResponseEntity.ok(service.authentication(request));
	}

}
