package com.company.controller;

import javax.servlet.http.HttpServletResponse;

import com.company.config.*;
import com.company.dto.QrCodeDTO;
import com.company.dto.SecurityCodeDTO;
import com.company.model.EmailBlacklist;
import com.company.repository.EmailBlacklistRepository;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import io.jsonwebtoken.ExpiredJwtException;
import com.company.dto.RegisteredUserDTO;
import com.company.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.company.model.User;
import com.company.service.UserService;

import java.util.Calendar;

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

	@Autowired
	private TwoFactorAuthenticator twoFactorAuthenticator;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private EmailBlacklistRepository emailBlacklistRepository;
	
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

		String jwt = tokenUtils.generateToken(user.getId(), user.getUsername(), String.valueOf(user.getRoles().get(0)));
		int expiresIn = tokenUtils.getExpiredIn();

		String refreshJwt = tokenUtils.generateRefreshToken(user.getId(), user.getUsername(), String.valueOf(user.getRoles().get(0)));
		int expiresInRefresh = tokenUtils.getExpiredInRefreshToken();

        if(!user.isTfa()){
            return ResponseEntity.ok(new UserTokenState(jwt, refreshJwt, expiresIn, expiresInRefresh));
        }else{
            return new ResponseEntity<UserTokenState>(new UserTokenState(), HttpStatus.OK);
        }

	}

    @PostMapping("/qrcode")
    public ResponseEntity<UserTokenState> refresh(
            @RequestBody SecurityCodeDTO securityCodeDTO) {
        User user = this.userService.findByUsername(securityCodeDTO.username);

        String jwt = tokenUtils.generateToken(user.getId(), user.getUsername(), String.valueOf(user.getRoles().get(0)));
        int expiresIn = tokenUtils.getExpiredIn();

        String refreshJwt = tokenUtils.generateRefreshToken(user.getId(), user.getUsername(), String.valueOf(user.getRoles().get(0)));
        int expiresInRefresh = tokenUtils.getExpiredInRefreshToken();

        if (twoFactorAuthenticator.verifyCode(user.getSecretKey(), Integer.parseInt(securityCodeDTO.securityCode))) {
            return ResponseEntity.ok(new UserTokenState(jwt, refreshJwt, expiresIn, expiresInRefresh));
        } else {
            return new ResponseEntity<UserTokenState>(new UserTokenState(), HttpStatus.BAD_REQUEST);
        }
    }
	@PostMapping("/refresh")
	public ResponseEntity<UserTokenState> refresh(
            @RequestBody String refreshToken) {
		int expiresIn = tokenUtils.getExpiredIn();
		int expiresInRefresh = tokenUtils.getExpiredInRefreshToken();
		try{
			int id = tokenUtils.getIdFromToken(refreshToken);
			User user = userService.findById(id);

			if (tokenUtils.validateToken(refreshToken, user)) {
				String jwt = tokenUtils.generateToken(id, user.getUsername(), String.valueOf(user.getRoles().get(0)));
				return new ResponseEntity<UserTokenState>(new UserTokenState(jwt, refreshToken, expiresIn, expiresInRefresh), HttpStatus.OK);
			}else{
				return new ResponseEntity<UserTokenState>(new UserTokenState(),HttpStatus.UNAUTHORIZED);
			}
		} catch (ExpiredJwtException ex) {
			return new ResponseEntity<UserTokenState>(new UserTokenState(),HttpStatus.UNAUTHORIZED);
		}
	}


	@PostMapping("/registration")
	public ResponseEntity<RegisteredUserDTO> addUser(@RequestBody RegisteredUserDTO registeredUserDTO, UriComponentsBuilder ucBuilder) {

		EmailBlacklist eb = emailBlacklistRepository.findByEmail(registeredUserDTO.getEmail());

		Calendar now = Calendar.getInstance();

		if(eb != null){
			if(eb.getBlacklistedUntil().after(now)){
				throw new IllegalArgumentException("Blacklisted email");
			}
		}

		User existUser = this.userService.findByUsername(registeredUserDTO.getEmail());

		if (existUser != null) {
			throw new ResourceConflictException(registeredUserDTO.getId(), "Email already in use");
		}

		//Address address = new Address(registeredUserDTO.getState(), registeredUserDTO.getCity(), registeredUserDTO.getStreet(), registeredUserDTO.getNumber());
		registeredUserDTO.setPassword(passwordEncoder.encode(registeredUserDTO.getPassword()));

		userService.registerUser(registeredUserDTO);
		// treba staviti da se uzme id od ovog registrovanog usera i da mu se stavi role_user
		//System.out.println(registeredUserDTO.getEmail());

		userService.createRegisterRequest(registeredUserDTO);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/verify-email/{email}")
	public Boolean verifyEmail(@PathVariable String email){
		User user = userService.findByUsername(email);
		if (user == null) {
			return false;
		}
		user.setEnabled(true);
		userService.save(user);
		return true;
	}

}