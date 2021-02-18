package com.example.m3.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.m1.dao.AccountDao;
import com.example.m1.model.Account;
import com.example.m1.model.Role;
import com.example.m3.request.SignupRequest;
import com.example.m3.response.MessageResponse;

@RestController
public class SignUpController {
	
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	AccountDao accountDao;

	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (accountDao.checkUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (accountDao.checkEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Account user = new Account(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

//		Set<String> strRoles = signUpRequest.getRole();
//		Set<Role> roles = new HashSet<>();
//
//		if (strRoles == null) {
//				throw new RuntimeException("Error: Role is not found.");
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "writer":
//					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//				case "reader":
////					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
////							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(modRole);
//
//					break;
//				default:
//					throw new RuntimeException("Error: Role is not found.");
//				}
//			});
//		
//
//		user.setRoles(roles);
//		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
