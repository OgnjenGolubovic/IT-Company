package com.company.controller;

import com.company.dto.RoleDTO;
import com.company.mapper.RoleMapper;
import com.company.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "roles")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;
    @PreAuthorize("hasPermission(#id, 'Role', 'read')")
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll(HttpServletRequest request) {
        return new ResponseEntity<>(roleMapper.ToDTO(roleService.getAll()), HttpStatus.OK);
    }
    @PreAuthorize("hasPermission(#id, 'Role', 'update')")
    @PostMapping
    public ResponseEntity<?> update(@RequestBody RoleDTO roleDTO) {
        roleService.update(roleMapper.ToModel(roleDTO));
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
