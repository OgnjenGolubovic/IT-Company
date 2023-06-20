package com.company.service;

import com.company.config.PrivateKeyEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.model.User;
import com.company.repository.UserRepository;

// Ovaj servis je namerno izdvojen kao poseban u ovom primeru.
// U opstem slucaju UserServiceImpl klasa bi mogla da implementira UserDetailService interfejs.
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PrivateKeyEncryption privateKeyEncryption;

	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(privateKeyEncryption.encryptToString(username));
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", username));
		} else {
			decryptUser(user);
			return user;
		}
	}
	private void decryptUser(User user){
		user.setUsername(privateKeyEncryption.decryptFromString(user.getUsername()));
		user.setPhoneNumber(privateKeyEncryption.decryptFromString(user.getPhoneNumber()));
		user.setStreetNumber(privateKeyEncryption.decryptFromString(user.getStreetNumber()));
		user.setStreet(privateKeyEncryption.decryptFromString(user.getStreet()));
		user.setCity(privateKeyEncryption.decryptFromString(user.getCity()));
		user.setState(privateKeyEncryption.decryptFromString(user.getPhoneNumber()));
		user.setName(privateKeyEncryption.decryptFromString(user.getPhoneNumber()));
		user.setSurname(privateKeyEncryption.decryptFromString(user.getPhoneNumber()));
	}
}
