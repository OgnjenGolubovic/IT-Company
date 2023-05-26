package com.company.controller;

import com.company.dto.PermissionDTO;
import com.company.mapper.PermissionMapper;
import com.company.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> getAll(HttpServletRequest request) {
        return new ResponseEntity<>(permissionMapper.ToDTO(permissionService.getAll()), HttpStatus.OK);
    }
}
