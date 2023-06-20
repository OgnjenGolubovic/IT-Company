package com.company.service;

import com.company.config.PrivateKeyEncryption;
import com.company.model.ProjectManager;
import com.company.repository.ProjectMangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProjectManagerService {
    @Autowired
    private ProjectMangerRepository projectMangerRepository;
    @Autowired
    private PrivateKeyEncryption privateKeyEncryption;
    public int findByUsername(String username) throws UsernameNotFoundException {
        ProjectManager projectManager = projectMangerRepository.findByUsername(privateKeyEncryption.encryptToString(username));
        if(projectManager != null){
            return projectManager.getId();
        }
        return 0;
    }
}
