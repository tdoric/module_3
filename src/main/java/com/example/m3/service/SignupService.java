package com.example.m3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.m1.dao.AccountDao;
import com.example.m1.model.Account;
import com.example.m3.request.SignupRequest;
import com.example.m3.response.MessageResponse;

@Service
public class SignupService {
	
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	AccountDao accountDao;
	
	public ResponseEntity<MessageResponse>  processSignUp(SignupRequest request) {
		
		if (accountDao.checkUsername(request.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (accountDao.checkEmail(request.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		
		// Create new user's account
		Account user = new Account(request.getUsername(), 
									request.getEmail(),
									 encoder.encode(request.getPassword()));
		
		accountDao.insertAccount(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

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
	}

}
