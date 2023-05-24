package com.company.service;

import java.util.List;

import com.company.dto.RegisteredUserDTO;
//import com.company.mappers.RegisteredUserMapper;
import com.company.model.RegistrationRequest;
import com.company.model.enums.CompanyRole;
import com.company.repository.RegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.model.User;
import com.company.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RegistrationRequestRepository registrationRequestRepository;

	public User findByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public User findById(Integer id) throws AccessDeniedException {
		return userRepository.findById(id).orElseGet(null);
	}

	public List<User> findAll() throws AccessDeniedException {
		return userRepository.findAll();
	}

	public User registerUser(RegisteredUserDTO userRequest) {

		CompanyRole cr;

		if(userRequest.getCompanyRole().equals("humanResourceManager")){
			cr = CompanyRole.HR;
		} else if (userRequest.getCompanyRole().equals("softwareEngineer")) {
			cr = CompanyRole.SOFTWARE_ENGINEER;
		} else if (userRequest.getCompanyRole().equals("projectManager")) {
			cr = CompanyRole.PROJECT_MANAGER;
		} else{
			cr = null;
		}

		User u = new User(userRequest.getEmail(), userRequest.getPassword(), userRequest.getName(), userRequest.getSurname(),
				userRequest.getState(), userRequest.getCity(), userRequest.getStreet(), userRequest.getStreetNumber(), userRequest.getPhone(), cr);
		//u = new RegisteredUserMapper(userRequest);

		//u.setUsername(userRequest.getUsername());
		
		// pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
		// treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
		//u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		/*
		u.setFirstName(userRequest.getFirstname());
		u.setLastName(userRequest.getLastname());
		u.setEnabled(true);
		u.setEmail(userRequest.getEmail());

		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		List<Role> roles = roleService.findByName("ROLE_USER");
		u.setRoles(roles);*/
		
		return this.userRepository.save(u);
	}


    public void save(User user) {
		this.userRepository.save(user);
    }

	public void createRegisterRequest(RegisteredUserDTO userRequest) {

		CompanyRole cr;

		if(userRequest.getCompanyRole().equals("humanResourceManager")){
			cr = CompanyRole.HR;
		} else if (userRequest.getCompanyRole().equals("softwareEngineer")) {
			cr = CompanyRole.SOFTWARE_ENGINEER;
		} else if (userRequest.getCompanyRole().equals("projectManager")) {
			cr = CompanyRole.PROJECT_MANAGER;
		} else{
			cr = null;
		}

		RegistrationRequest registrationRequest = new RegistrationRequest(userRequest.getEmail(), userRequest.getName(), userRequest.getSurname(), cr);

		this.registrationRequestRepository.save(registrationRequest);

	}
}
