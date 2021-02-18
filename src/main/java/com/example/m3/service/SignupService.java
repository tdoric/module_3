package com.example.m3.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.m1.dao.AccountDao;
import com.example.m1.dao.RoleDao;
import com.example.m1.model.Account;
import com.example.m1.model.RoleAccount;
import com.example.m3.request.SignupRequest;
import com.example.m3.response.MessageResponse;

@Service
@Transactional
public class SignupService {
	
	PasswordEncoder encoder;
	AccountDao accountDao;
	RoleDao roleDao;
	
	@Autowired
	public SignupService(PasswordEncoder encoder, AccountDao accountDao, RoleDao roleDao) {
		this.encoder = encoder;
		this.accountDao = accountDao;
		this.roleDao = roleDao;
	}
	
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
		List<Integer> defaultValues = Arrays.asList(1,2);
		
		for( Integer idRole : request.getRole()) {
			if(!defaultValues.contains(idRole)) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Role is not in system!"));
			}
				
		}
		// Create new user's account
		Account user = new Account(request.getUsername(), 
									request.getEmail(),
									 encoder.encode(request.getPassword()));
		
		Integer userId=accountDao.insertAccount(user);
		List<RoleAccount> roleAccs = new ArrayList<>();
		for(Integer idRole : request.getRole()) {
			RoleAccount roleAcc = new RoleAccount();
			roleAcc.setRoleId(idRole);
			roleAcc.setUserId(userId);
			roleAccs.add(roleAcc);
		}
		roleDao.insertRolesForUser(roleAccs);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}
	

}
