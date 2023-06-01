package com.company.config;

import com.company.model.Permission;
import com.company.model.Role;
import com.company.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
@Component
public class PermissionUtil implements PermissionEvaluator {
    @Override
    public boolean hasPermission(
            Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();

        return hasPrivilege(auth, targetType, permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(
            Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(),
                permission.toString().toUpperCase());
    }
    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
        for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
            Role role = (Role) grantedAuth;
            if(permission == null || !checkIfPermissionExists(role, targetType, permission)){
                return false;
            }
        }
        return true;
    }
    private boolean checkIfPermissionExists(Role role, String targetType, String permission){
        for(Permission rolePermission : role.getPermissions()){
            if(rolePermission.getName().startsWith(targetType) &&
                    rolePermission.getName().contains(permission))return true;
        }
        return false;
    }
}
