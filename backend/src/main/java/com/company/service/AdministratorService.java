package com.company.service;

import com.company.dto.AdminDTO;
import com.company.dto.RegisterRequestDTO;
import com.company.dto.enums.Status;
import com.company.model.Administrator;
import com.company.model.EmailBlacklist;
import com.company.model.RegistrationRequest;
import com.company.model.User;
import com.company.repository.AdministratorRepository;
import com.company.repository.EmailBlacklistRepository;
import com.company.repository.RegistrationRequestRepository;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailBlacklistRepository emailBlacklistRepository;

    public int findByUsername(String username) throws UsernameNotFoundException {
        Administrator administrator = administratorRepository.findByUsername(username);
        if(administrator != null){
            return administrator.getId();
        }
        return 0;
    }


    public List<RegisterRequestDTO> findAllPendingRequestsDTO() {

        List<RegistrationRequest> registrationRequests;

        registrationRequests = this.registrationRequestRepository.findAll();

        List<RegisterRequestDTO> ret = new ArrayList<>();

        for(RegistrationRequest request : registrationRequests){

            if(request.getStatus().equals(Status.PENDING)) {
                RegisterRequestDTO req = new RegisterRequestDTO(request.getEmail(),
                        request.getName(), request.getSurname(), request.getCompanyRole());

                ret.add(req);
            }
        }

        return ret;
    }

    public List<RegistrationRequest> findAllRequests() {
        return this.registrationRequestRepository.findAll();
    }

    public void approveRegistration(RegisterRequestDTO registerRequestDTO) {

        //Stavljamo status registration requesta na approved
        List<RegistrationRequest> registrationRequests;
        registrationRequests = registrationRequestRepository.findAll();

        RegistrationRequest temp = new RegistrationRequest();

        for(RegistrationRequest request: registrationRequests){
            if(request.getEmail().equals(registerRequestDTO.getEmail())){
                temp = request;
            }
        }

        temp.setStatus(Status.APPROVED);
        registrationRequestRepository.save(temp);

        //Sad saljemo aktivacioni kod useru sa tom email adresom.
       	emailSenderService.sendSimpleEmail(registerRequestDTO.getEmail(),
				"Verifikacija naloga",
				"Molimo Vas kliknite na link da biste izvršili verifikaciju vašeg naloga: http://localhost:8084/auth/verify-email/" + registerRequestDTO.getEmail());


    }


    public void cancelRegistration(RegisterRequestDTO registerRequestDTO) {

        //Stavljamo status registration requesta na approved
        List<RegistrationRequest> registrationRequests;
        registrationRequests = registrationRequestRepository.findAll();

        RegistrationRequest temp = new RegistrationRequest();

        for(RegistrationRequest request: registrationRequests){
            if(request.getEmail().equals(registerRequestDTO.getEmail())){
                temp = request;
            }
        }

        temp.setStatus(Status.CANCELLED);
        registrationRequestRepository.save(temp);

        //Stavljamo mejl koji je koriscen za registraciju na blacklistu da ne moze da se prijavi narednih sat vremena
        Calendar until = Calendar.getInstance();
        until.add(Calendar.HOUR_OF_DAY, 1);
        EmailBlacklist emailBlacklist = new EmailBlacklist(registerRequestDTO.getEmail(), until);

        emailBlacklistRepository.save(emailBlacklist);

        //Sad saljemo mejl useru sa tekstom odbijanja.
        emailSenderService.sendSimpleEmail(registerRequestDTO.getEmail(),
                "Verifikacija naloga odbijena",
                "Korisnik sa email adresom: " + registerRequestDTO.getEmail() + " je odbijen.");


    }


    public void updateProfile(AdminDTO adminDTO) {

        Administrator a = administratorRepository.findByUsername(adminDTO.getEmail());

        a.setName(adminDTO.getName());
        a.setSurname(adminDTO.getSurname());
        a.setState(adminDTO.getState());
        a.setCity(adminDTO.getCity());
        a.setStreet(adminDTO.getStreet());
        a.setStreetNumber(adminDTO.getStreetNumber());
        a.setPhoneNumber(adminDTO.getPhone());

        administratorRepository.save(a);
    }
}
