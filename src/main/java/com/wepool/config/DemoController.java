package com.wepool.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
	
	
	@GetMapping("/hello")
	public ResponseEntity<String> sayhelllo()
	{
		return  ResponseEntity.ok("hello from secured Endpoint");
	}

}
