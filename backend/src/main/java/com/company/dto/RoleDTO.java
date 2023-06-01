package com.company.dto;

import java.util.List;

public class RoleDTO {
    public Long id;
    public String name;
    public List<PermissionDTO> permissions;

    public RoleDTO() {
    }
}
