package com.example.m3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.example.m4.config.WebSecurityConfig;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfig extends WebSecurityConfig {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.authorizeRequests().antMatchers("/api/auth/signup").permitAll();
	}
	
	
	

}