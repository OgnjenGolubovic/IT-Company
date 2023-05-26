package com.company.mapper;

import com.company.dto.PermissionDTO;
import com.company.model.Permission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionMapper implements IMapper<Permission, PermissionDTO>{
    @Override
    public Permission ToModel(PermissionDTO permissionDTO) {
        Permission permission = new Permission();
        if(permissionDTO.id != null) permission.setId(permissionDTO.id);
        permission.setName(permissionDTO.name);
        return permission;
    }

    @Override
    public List<Permission> ToModel(List<PermissionDTO> dto) {
        List<Permission> permissions = new ArrayList<>();

        for(PermissionDTO permissionDTO : dto){
            permissions.add(ToModel(permissionDTO));
        }

        return permissions;
    }

    @Override
    public PermissionDTO ToDTO(Permission permission) {
        PermissionDTO permissionDTO =  new PermissionDTO();
        permissionDTO.id  = permission.getId();
        permissionDTO.name = permission.getName();
        return permissionDTO;
    }

    @Override
    public List<PermissionDTO> ToDTO(List<Permission> model) {
        List<PermissionDTO> permissionDTOS = new ArrayList<>();

        for(Permission permission : model){
            permissionDTOS.add(ToDTO(permission));
        }

        return permissionDTOS;
    }
}
