package com.company.service;

import com.company.model.SoftwareEngineer;
import com.company.repository.SoftwareEngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SoftwareEngineerService {
    @Autowired
    private SoftwareEngineerRepository softwareEngineerRepository;
    public int findByUsername(String username) throws UsernameNotFoundException {
        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findByUsername(username);
        if(softwareEngineer != null){
            return softwareEngineer.getId();
        }
        return 0;
    }
}
