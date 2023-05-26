package com.company.config;

import com.company.model.Permission;
import com.company.model.Role;
import com.company.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PermissionUtil {
    @Autowired
    private PermissionService permissionService;
    public void checkPermission(String permissionName, Authentication authentication) throws AccessDeniedException {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            Role role = (Role) authority;
            Permission permission = this.permissionService.findByName(permissionName);
            if(permission == null || !checkIfPermissionExists(role, permission)){
                throw new AccessDeniedException("No right permission");
            }
        }
    }
    private boolean checkIfPermissionExists(Role role, Permission permission){
        for(Permission rolePermission : role.getPermissions()){
            if(Objects.equals(rolePermission.getId(), permission.getId()))return true;
        }
        return false;
    }
}
