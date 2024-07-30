package com.SpringSecurity01.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {

		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

		return bcpe;
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

		
		//--authorities and Role
		
		UserDetails admin = User
				.withUsername("admin")
				.password(passwordEncoder.encode("1"))
				.roles("ADMIN", "USER")
				
				.build();
		
		
		UserDetails user = User
				.withUsername("customer")
				.password(passwordEncoder.encode("2"))
				.roles("CUSTOMER")
				.build();
		

		InMemoryUserDetailsManager imm = new InMemoryUserDetailsManager(admin, user);

		return imm;
	}

	// --for auth and permit all
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity
		        .authorizeHttpRequests()
				.anyRequest()
				.authenticated()
//		        .requestMatchers(HttpMethod.GET, "/special").hasAnyRole("ADMIN")
//		        .requestMatchers(HttpMethod.GET, "/basic").hasAnyRole("ADMIN","USER")
		        .and()
		        .formLogin(Customizer.withDefaults())
		        .httpBasic(Customizer.withDefaults())
		        .build();
		

		
	    
	}
	
	

}
