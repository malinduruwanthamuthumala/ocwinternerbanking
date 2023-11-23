package com.occw.occwinternetbanking.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.Protocol;
import com.occw.occwinternetbanking.jpa.components.Authority;
import com.occw.occwinternetbanking.jpa.components.Customer;
import com.occw.occwinternetbanking.jpa.repository.CustomerRepository;

@Component
public class occwAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
		
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<Customer> customerList = customerRepository.findByEmail(userName);
		if(customerList.size() > 0) {
			if(passwordEncoder.matches(password, customerList.get(0).getPassword())) {
				return new UsernamePasswordAuthenticationToken(userName,password,getGrantedAuthority(customerList.get(0).getAuthorities()));
			} else {
				throw new BadCredentialsException("invalid password");
			}
			
		} else {
			throw new BadCredentialsException("No user found");
		}
		
	}
	
	private List<GrantedAuthority> getGrantedAuthority(List<Authority> authorities){
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Authority authority: authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}
		return grantedAuthorities;
	}
	

}
