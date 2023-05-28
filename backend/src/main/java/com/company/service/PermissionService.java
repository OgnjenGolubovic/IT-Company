package com.company.service;

import com.company.model.Permission;
import com.company.model.Role;
import com.company.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Permission> getAllUnownedByRole(Role role){
        List<Permission> permissions = new ArrayList<>();
        for(Permission permission : getAll()){
            if(checkIfPermissionNotExist(role, permission))permissions.add(permission);
        }
        return permissions;
    }
    private boolean checkIfPermissionNotExist(Role role, Permission permission){
        for(Permission rolePermission : role.getPermissions()){
            if(rolePermission.getName().equals(permission.getName()))return false;
        }
        return true;
    }
}
