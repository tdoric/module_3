package com.example.m3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.m3.request.SignupRequest;
import com.example.m3.response.MessageResponse;
import com.example.m3.service.SignupService;

@RestController
@RequestMapping("/api/auth")
public class SignUpController {
	
	@Autowired
	SignupService service;

	
	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return service.processSignUp(signUpRequest);
	}

}
