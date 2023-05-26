package com.company.service;

import com.company.model.Permission;
import com.company.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findByName(String name){
        return permissionRepository.findPermissionByName(name);
    }

    public List<Permission> getAll(){
        return permissionRepository.findAll();
    }
}
