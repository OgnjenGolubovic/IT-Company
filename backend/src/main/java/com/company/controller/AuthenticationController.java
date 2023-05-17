package com.company.controller;

import javax.servlet.http.HttpServletResponse;

import com.company.dto.RegisteredUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.company.config.JwtAuthenticationRequest;
import com.company.config.ResourceConflictException;
import com.company.config.TokenUtils;
import com.company.config.UserTokenState;
import com.company.model.User;
import com.company.service.UserService;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
		// AuthenticationException
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();

		String jwt = tokenUtils.generateToken(user.getUsername(), String.valueOf(user.getRoles().get(0)));
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}


	@PostMapping("/register")
	public ResponseEntity<RegisteredUserDTO> addUser(@RequestBody RegisteredUserDTO registeredUserDTO, UriComponentsBuilder ucBuilder) {
		User existUser = this.userService.findByUsername(registeredUserDTO.getEmail());

		if (existUser != null) {
			throw new ResourceConflictException(registeredUserDTO.getId(), "Email already in use");
		}

		Address address = new Address(registeredUserDTO.getState(), registeredUserDTO.getCity(), registeredUserDTO.getStreet(), registeredUserDTO.getNumber());
		registeredUserDTO.setPassword(passwordEncoder.encode(registeredUserDTO.getPassword()));
		userService.RegisterUser(registeredUserDTO, address);
		// treba staviti da se uzme id od ovog registrovanog usera i da mu se stavi role_user

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}