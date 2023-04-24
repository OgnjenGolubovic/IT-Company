package com.company.service;

import com.company.model.Administrator;
import com.company.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;
    public int findByUsername(String username) throws UsernameNotFoundException {
        Administrator administrator = administratorRepository.findByUsername(username);
        if(administrator != null){
            return administrator.getId();
        }
        return 0;
    }
}
