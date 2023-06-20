package com.company.controller;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.company.model.User;
import com.company.service.UserService;

import java.util.Calendar;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@CrossOrigin(origins = "https://localhost:4200")
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

	private final static Logger logger = LogManager.getLogger(AuthenticationController.class);

	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest request, HttpServletResponse response) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			User user = (User) authentication.getPrincipal();
			//userService.decryptUser(user);
			String jwt = tokenUtils.generateToken(user.getId(), user.getUsername(), String.valueOf(user.getRoles().get(0)));
			int expiresIn = tokenUtils.getExpiredIn();

			String refreshJwt = tokenUtils.generateRefreshToken(user.getId(), user.getUsername(), String.valueOf(user.getRoles().get(0)));
			int expiresInRefresh = tokenUtils.getExpiredInRefreshToken();

			String address = request.getHeader("x-forwarded-for");
			if (address == null || address.length() == 0) {
				address = request.getHeader("http-x-forwarded-for");
				if (address == null || address.length() == 0) {
					address = request.getHeader("remote-addr");
					if (address == null || address.length() == 0) {
						address = request.getRemoteAddr();
					}
				}
			}
			logger.info("User successfully logged in from Host:"+ address +", Port:"+request.getRemotePort());

			if (!user.isTfa()) {
				return ResponseEntity.ok(new UserTokenState(jwt, refreshJwt, expiresIn, expiresInRefresh));
			} else {
				return new ResponseEntity<UserTokenState>(new UserTokenState(), HttpStatus.OK);
			}
		}catch(BadCredentialsException ex) {
			String address = request.getHeader("x-forwarded-for");
			if (address == null || address.length() == 0) {
				address = request.getHeader("http-x-forwarded-for");
				if (address == null || address.length() == 0) {
					address = request.getHeader("remote-addr");
					if (address == null || address.length() == 0) {
						address = request.getRemoteAddr();
					}
				}
			}
			logger.warn("User unsuccessfully tried to login in from Host:"+ address +", Port:"+request.getRemotePort() + " - Bad Credentials");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

    @PostMapping("/qrcode")
    public ResponseEntity<UserTokenState> refresh(
            @RequestBody SecurityCodeDTO securityCodeDTO,
			HttpServletRequest request) {
        User user = this.userService.findByUsername(securityCodeDTO.username);

        String jwt = tokenUtils.generateToken(user.getId(), user.getUsername(), String.valueOf(user.getRoles().get(0)));
        int expiresIn = tokenUtils.getExpiredIn();

        String refreshJwt = tokenUtils.generateRefreshToken(user.getId(), user.getUsername(), String.valueOf(user.getRoles().get(0)));
        int expiresInRefresh = tokenUtils.getExpiredInRefreshToken();

		String secretKey = userService.getSecretKey(securityCodeDTO.username);

        if (twoFactorAuthenticator.verifyCode(secretKey, Integer.parseInt(securityCodeDTO.securityCode))) {
			String address = request.getHeader("x-forwarded-for");
			if (address == null || address.length() == 0) {
				address = request.getHeader("http-x-forwarded-for");
				if (address == null || address.length() == 0) {
					address = request.getHeader("remote-addr");
					if (address == null || address.length() == 0) {
						address = request.getRemoteAddr();
					}
				}
			}
			logger.info("User successfully logged in with qrcode from Host:"+ address +", Port:"+request.getRemotePort());
            return ResponseEntity.ok(new UserTokenState(jwt, refreshJwt, expiresIn, expiresInRefresh));
        } else {
            return new ResponseEntity<UserTokenState>(new UserTokenState(), HttpStatus.BAD_REQUEST);
        }
    }
	@PostMapping("/refresh")
	public ResponseEntity<UserTokenState> refresh(
            @RequestBody String refreshToken,
			HttpServletRequest request) {
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
			String address = request.getHeader("x-forwarded-for");
			if (address == null || address.length() == 0) {
				address = request.getHeader("http-x-forwarded-for");
				if (address == null || address.length() == 0) {
					address = request.getHeader("remote-addr");
					if (address == null || address.length() == 0) {
						address = request.getRemoteAddr();
					}
				}
			}
			logger.info("User unsuccessfully logged in from Host:"+ address +", Port:" + request.getRemotePort() + " - expired Access Token");
			return new ResponseEntity<UserTokenState>(new UserTokenState(),HttpStatus.UNAUTHORIZED);
		}
	}


	@PostMapping("/registration")
	public ResponseEntity<RegisteredUserDTO> addUser(@RequestBody RegisteredUserDTO registeredUserDTO, HttpServletRequest request, UriComponentsBuilder ucBuilder) {

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

		userService.createRegisterRequest(registeredUserDTO);

		userService.registerUser(registeredUserDTO);
		// treba staviti da se uzme id od ovog registrovanog usera i da mu se stavi role_user
		//System.out.println(registeredUserDTO.getEmail());

		userService.createRegisterRequest(registeredUserDTO);

		String address = request.getHeader("x-forwarded-for");
		if (address == null || address.length() == 0) {
			address = request.getHeader("http-x-forwarded-for");
			if (address == null || address.length() == 0) {
				address = request.getHeader("remote-addr");
				if (address == null || address.length() == 0) {
					address = request.getRemoteAddr();
				}
			}
		}
		logger.info("User successfully registered from Host:"+ address +", Port:" + request.getRemotePort());

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasPermission(#id, 'Administrator', 'create')")
	@PostMapping("/adminRegistration")
	public ResponseEntity<RegisteredUserDTO> addAdministrator(@RequestBody RegisteredUserDTO registeredUserDTO, HttpServletRequest request, UriComponentsBuilder ucBuilder) {

		User existUser = this.userService.findByUsername(registeredUserDTO.getEmail());

		if (existUser != null) {
			throw new ResourceConflictException(registeredUserDTO.getId(), "Email already in use");
		}

		//Address address = new Address(registeredUserDTO.getState(), registeredUserDTO.getCity(), registeredUserDTO.getStreet(), registeredUserDTO.getNumber());
		registeredUserDTO.setPassword(passwordEncoder.encode(registeredUserDTO.getPassword()));

		userService.registerAdmin(registeredUserDTO);
		// treba staviti da se uzme id od ovog registrovanog usera i da mu se stavi role_user
		//System.out.println(registeredUserDTO.getEmail());

		String address = request.getHeader("x-forwarded-for");
		if (address == null || address.length() == 0) {
			address = request.getHeader("http-x-forwarded-for");
			if (address == null || address.length() == 0) {
				address = request.getHeader("remote-addr");
				if (address == null || address.length() == 0) {
					address = request.getRemoteAddr();
				}
			}
		}
		logger.info("Admin successfully registered from Host:"+ address +", Port:" + request.getRemotePort());

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