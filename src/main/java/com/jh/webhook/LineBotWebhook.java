package com.jh.webhook;

import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.profile.UserProfileResponse;

@RestController
public class LineBotWebhook {

	private final Logger logger = LogManager.getLogger(LineBotWebhook.class);
	
	
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

		
		
		
		logger.info("body=" + body);
		logger.info("X-Line-Sig=" + signature);
		
		return body + ",sig=" + signature;
	}	
	
	
	
	public String getUserProfile(String userId) {
		final LineMessagingClient client = LineMessagingClient
		        .builder("<channel access token>")
		        .build();

		final UserProfileResponse userProfileResponse;
		try {
		    userProfileResponse = client.getProfile(userId).get();
		} catch (InterruptedException | ExecutionException e) {
		    e.printStackTrace();
		    return "";
		}

		logger.info(userProfileResponse.getUserId());
		logger.info(userProfileResponse.getDisplayName());
		logger.info(userProfileResponse.getPictureUrl());
		
		return userProfileResponse.toString();
	}
	


	
}
