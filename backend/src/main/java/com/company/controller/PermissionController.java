package com.company.controller;

import com.company.dto.PermissionDTO;
import com.company.dto.RoleDTO;
import com.company.mapper.PermissionMapper;
import com.company.mapper.RoleMapper;
import com.company.model.Role;
import com.company.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "permissions")
@CrossOrigin
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;
    @PostMapping(value = "/unowned")
    public ResponseEntity<List<PermissionDTO>> getAllUnownedByRole(@RequestBody RoleDTO roleDTO) {
        return new ResponseEntity<>(permissionMapper.ToDTO(permissionService.getAllUnownedByRole(roleMapper.ToModel(roleDTO))), HttpStatus.OK);
    }
}
