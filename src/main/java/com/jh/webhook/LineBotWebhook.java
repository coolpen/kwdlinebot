package com.jh.webhook;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineBotWebhook {


	@RequestMapping("/")
	public String hello(){
		return "Hey, Spring Boot 的 Hello World ! ";
	}

	
	
	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("/line/webhook")
	public String lineWebhook2(
			@RequestParam(value="name") String name
			){
		return name;
	}
	
	
	
	@CrossOrigin(origins = "http://localhost:8888")
	@PostMapping("/line/webhook")
	public String lineWebhook(
			@RequestBody String body,
			@RequestHeader("X-Line-Signature") String signature
			){

		return body + ",sig=" + signature;
	}	
	
}
