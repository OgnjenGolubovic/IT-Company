package com.company.mapper;

import com.company.dto.RoleDTO;
import com.company.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RoleMapper implements IMapper<Role, RoleDTO>{

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public Role ToModel(RoleDTO roleDTO) {
        Role role = new Role();
        if(roleDTO.id != null)role.setId(roleDTO.id);
        role.setName(roleDTO.name);
        role.setPermissions(permissionMapper.ToModel(roleDTO.permissions));
        return role;
    }

    @Override
    public List<Role> ToModel(List<RoleDTO> dto) {
        List<Role> roles = new ArrayList<>();

        for(RoleDTO roleDTO : dto){
            roles.add(ToModel(roleDTO));
        }

        return roles;
    }

    @Override
    public RoleDTO ToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.id = role.getId();
        roleDTO.name = role.getName();
        roleDTO.permissions = permissionMapper.ToDTO(role.getPermissions());
        return roleDTO;
    }

    @Override
    public List<RoleDTO> ToDTO(List<Role> model) {
        List<RoleDTO> roleDTOS = new ArrayList<>();

        for(Role role : model){
            roleDTOS.add(ToDTO(role));
        }

        return roleDTOS;
    }
}
