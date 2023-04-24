package com.company.service;

import com.company.model.HumanResources;
import com.company.repository.HumanResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HumanResourcesService {
    @Autowired
    private HumanResourcesRepository humanResourcesRepository;
    public int findByUsername(String username) throws UsernameNotFoundException {
        HumanResources humanResources = humanResourcesRepository.findByUsername(username);
        if(humanResources != null){
            return humanResources.getId();
        }
        return 0;
    }
}
