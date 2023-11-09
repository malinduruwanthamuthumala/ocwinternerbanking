package com.occw.occwinternetbanking.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.occw.occwinternetbanking.jpa.components.Customer;
import com.occw.occwinternetbanking.jpa.repository.CustomerRepository;

@Service
public class occwUserDetailService  implements UserDetailsService{
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		String userName , passwprd = null;
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<Customer> customerList = customerRepository.findByEmail(email);
		
		if(customerList.size() == 0) {
			throw new UsernameNotFoundException("No user found for the email" + email);
		} else {
			 userName = customerList.get(0).getUsername();
			 passwprd = customerList.get(0).getPassword();
			 authorities.add(new SimpleGrantedAuthority(customerList.get(0).getRole()));
			 
		}
		return new User(userName,passwprd,authorities);
		
	}

}
