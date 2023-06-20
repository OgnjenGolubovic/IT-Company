package com.company.service;

import com.company.config.PrivateKeyEncryption;
import com.company.dto.RegisteredUserDTO;
import com.company.dto.UserDTO;
import com.company.dto.enums.Status;
import com.company.model.*;
import com.company.model.enums.CompanyRole;
import com.company.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PrivateKeyEncryption privateKeyEncryption;

	@Autowired
	private HumanResourcesRepository humanResourcesRepository;
	@Autowired
	private ProjectMangerRepository projectMangerRepository;
	@Autowired
	private SoftwareEngineerRepository softwareEngineerRepository;

	@Autowired
	private RegistrationRequestRepository registrationRequestRepository;

	public User findByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(privateKeyEncryption.encryptToString(username));
		if(user != null)decryptUser(user);
		return user;
	}

	public String getSecretKey(String username) {
		User user = userRepository.findByUsername(privateKeyEncryption.encryptToString(username));
		return privateKeyEncryption.decryptFromString(user.getSecretKey());
	}

	public User setTFAByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(privateKeyEncryption.encryptToString(username));
		user.setTfa(true);
		return userRepository.save(user);
	}

	public User setSecretKeyByUsername(User user, String secretKey){
		String encryptedSecretKey = privateKeyEncryption.encryptToString(secretKey);
		user.setSecretKey(encryptedSecretKey);
		encryptUser(user);
		return userRepository.save(user);
	}
	public User findById(Integer id) throws AccessDeniedException {
		User user = userRepository.findById(id).get();
		decryptUser(user);
		return user;
	}

	public List<User> findAll() throws AccessDeniedException {
		return decryptUsers(userRepository.findAll());
	}

	public User registerUser(RegisteredUserDTO userRequest) {

		CompanyRole cr;
		List<Role> roles;

        encryptRegisterData(userRequest);

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
		encryptUser(user);
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

	public void registerAdmin(RegisteredUserDTO registeredUserDTO) {

		List<Role> roles;

		roles = roleService.findByName("ROLE_ADMINISTRATOR");

		Administrator admin = new Administrator(registeredUserDTO.getEmail(), registeredUserDTO.getName(), registeredUserDTO.getSurname(), registeredUserDTO.getPassword(), roles);

		userRepository.save(admin);

	}
	private void encryptUser(User user){
		user.setPhoneNumber(privateKeyEncryption.encryptToString(user.getPhoneNumber()));
		user.setStreetNumber(privateKeyEncryption.encryptToString(user.getStreetNumber()));
		user.setStreet(privateKeyEncryption.encryptToString(user.getStreet()));
		user.setCity(privateKeyEncryption.encryptToString(user.getCity()));
		user.setState(privateKeyEncryption.encryptToString(user.getState()));
		user.setName(privateKeyEncryption.encryptToString(user.getName()));
		user.setSurname(privateKeyEncryption.encryptToString(user.getSurname()));
		user.setUsername(privateKeyEncryption.encryptToString(user.getUsername()));
	}
    private void encryptRegisterData(RegisteredUserDTO dto){
        dto.setPhone(privateKeyEncryption.encryptToString(dto.getPhone()));
        dto.setStreetNumber(privateKeyEncryption.encryptToString(dto.getStreetNumber()));
        dto.setStreet(privateKeyEncryption.encryptToString(dto.getStreet()));
        dto.setCity(privateKeyEncryption.encryptToString(dto.getCity()));
        dto.setState(privateKeyEncryption.encryptToString(dto.getState()));
        dto.setName(privateKeyEncryption.encryptToString(dto.getName()));
        dto.setSurname(privateKeyEncryption.encryptToString(dto.getSurname()));
        dto.setEmail(privateKeyEncryption.encryptToString(dto.getEmail()));
    }
	public void decryptUser(User user){
		user.setUsername(privateKeyEncryption.decryptFromString(user.getUsername()));
		user.setPhoneNumber(privateKeyEncryption.decryptFromString(user.getPhoneNumber()));
		user.setStreetNumber(privateKeyEncryption.decryptFromString(user.getStreetNumber()));
		user.setStreet(privateKeyEncryption.decryptFromString(user.getStreet()));
		user.setCity(privateKeyEncryption.decryptFromString(user.getCity()));
		user.setState(privateKeyEncryption.decryptFromString(user.getPhoneNumber()));
		user.setName(privateKeyEncryption.decryptFromString(user.getPhoneNumber()));
		user.setSurname(privateKeyEncryption.decryptFromString(user.getPhoneNumber()));
	}
	private List<User> decryptUsers(List<User> users){
		List<User> decryptedUsers = new ArrayList<>(users);
		for(User user: users){
			User decryptedUser = new User(user);
			decryptUser(decryptedUser);
			decryptedUsers.add(decryptedUser);
		}
		return decryptedUsers;
	}
}