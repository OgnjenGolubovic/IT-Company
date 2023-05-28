package com.company.service;

import java.util.Calendar;
import java.util.List;

import com.company.dto.RegisteredUserDTO;
//import com.company.mappers.RegisteredUserMapper;
import com.company.dto.UserDTO;
import com.company.dto.enums.Status;
import com.company.model.*;
import com.company.model.enums.CompanyRole;
import com.company.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HumanResourcesRepository humanResourcesRepository;
	@Autowired
	private ProjectMangerRepository projectMangerRepository;
	@Autowired
	private SoftwareEngineerRepository softwareEngineerRepository;

	@Autowired
	private RegistrationRequestRepository registrationRequestRepository;

	public User findByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public User setTFAByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		user.setTfa(true);
		return userRepository.save(user);
	}
	public User setSecretKeyByUsername(String username, String secretKey){
		User user = userRepository.findByUsername(username);
		user.setSecretKey(secretKey);
		return userRepository.save(user);
	}
	public User findById(Integer id) throws AccessDeniedException {
		return userRepository.findById(id).orElseGet(null);
	}

	public List<User> findAll() throws AccessDeniedException {
		return userRepository.findAll();
	}

	public User registerUser(RegisteredUserDTO userRequest) {

		CompanyRole cr;
		List<Role> roles;

		if(userRequest.getCompanyRole().equals("humanResourceManager")){
			cr = CompanyRole.HR;
			roles = roleService.findByName("ROLE_HUMAN_RESOURCES");
			HumanResources hr = new HumanResources(userRequest.getEmail(), userRequest.getPassword(), userRequest.getName(), userRequest.getSurname(),
					userRequest.getState(), userRequest.getCity(), userRequest.getStreet(), userRequest.getStreetNumber(), userRequest.getPhone(), cr, roles);
			return this.humanResourcesRepository.save(hr);
		} else if (userRequest.getCompanyRole().equals("softwareEngineer")) {
			cr = CompanyRole.SOFTWARE_ENGINEER;
			roles = roleService.findByName("ROLE_SOFTWARE_ENGINEER");
			SoftwareEngineer se = new SoftwareEngineer(userRequest.getEmail(), userRequest.getPassword(), userRequest.getName(), userRequest.getSurname(),
					userRequest.getState(), userRequest.getCity(), userRequest.getStreet(), userRequest.getStreetNumber(), userRequest.getPhone(), cr, Calendar.getInstance(), roles);
			return this.softwareEngineerRepository.save(se);
		} else if (userRequest.getCompanyRole().equals("projectManager")) {
			cr = CompanyRole.PROJECT_MANAGER;
			roles = roleService.findByName("ROLE_PROJECT_MANAGER");
			ProjectManager pm = new ProjectManager(userRequest.getEmail(), userRequest.getPassword(), userRequest.getName(), userRequest.getSurname(),
					userRequest.getState(), userRequest.getCity(), userRequest.getStreet(), userRequest.getStreetNumber(), userRequest.getPhone(), cr, roles);
			return this.projectMangerRepository.save(pm);
		} else{
			cr = null;
			User u = new User(userRequest.getEmail(), userRequest.getPassword(), userRequest.getName(), userRequest.getSurname(),
					userRequest.getState(), userRequest.getCity(), userRequest.getStreet(), userRequest.getStreetNumber(), userRequest.getPhone(), cr);
			return this.userRepository.save(u);
		}


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

		RegistrationRequest registrationRequest = new RegistrationRequest(userRequest.getEmail(), userRequest.getName(), userRequest.getSurname(), cr, Status.PENDING);

		this.registrationRequestRepository.save(registrationRequest);

	}

    public List<UserDTO> getAllUsers() {
		List<User> users = findAll();
		List<UserDTO> ret = new ArrayList<>();

		for(User user : users){
			if(user.isEnabled()){
				String cr;
				if(user.getCompanyRole().equals(CompanyRole.SOFTWARE_ENGINEER)){
					cr = "Software Engineer";
				} else if (user.getCompanyRole().equals(CompanyRole.HR)) {
					cr = "Human Resource Manager";
				} else if (user.getCompanyRole().equals(CompanyRole.PROJECT_MANAGER)) {
					cr = "Project Manager";
				} else { cr = "Administrator"; }


				UserDTO u = new UserDTO(user.getName(), user.getSurname(), cr, user.getPhoneNumber());
				ret.add(u);
			}
		}

		return ret;
    }
}


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
