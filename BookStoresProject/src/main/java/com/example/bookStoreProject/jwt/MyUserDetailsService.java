package com.example.bookStoreProject.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Users;
import com.example.bookStoreProject.repository.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository userRepository;
	Users user;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		user = userRepository.getUserByUserName(username);
		if(user != null) {
			return (UserDetails) new User(user.getUsername(), user.getPasscode(), new ArrayList<>());
		}
		throw new UsernameNotFoundException("User Not Find By the email"+username);
	}
	
	public Users getUserDetails() {
		return user;
	}
	
}
