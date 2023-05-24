package com.company.service;

import com.company.dto.RegisterRequestDTO;
import com.company.model.Administrator;
import com.company.model.RegistrationRequest;
import com.company.repository.AdministratorRepository;
import com.company.repository.RegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    public int findByUsername(String username) throws UsernameNotFoundException {
        Administrator administrator = administratorRepository.findByUsername(username);
        if(administrator != null){
            return administrator.getId();
        }
        return 0;
    }


    public List<RegisterRequestDTO> findAllRequests() {

        List<RegistrationRequest> temp;

        temp = this.registrationRequestRepository.findAll();

        List<RegisterRequestDTO> ret = new ArrayList<>();

        for(RegistrationRequest request : temp){

            RegisterRequestDTO req = new RegisterRequestDTO(request.getEmail(),
                    request.getName(), request.getSurname(), request.getCompanyRole());

            ret.add(req);
        }

        return ret;
    }
}
