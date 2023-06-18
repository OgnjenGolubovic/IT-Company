package com.company.controller;

import com.company.dto.RoleDTO;
import com.company.mapper.RoleMapper;
import com.company.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final static Logger logger = LogManager.getLogger(AuthenticationController.class);
    @PreAuthorize("hasPermission(#id, 'Role', 'read')")
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll(HttpServletRequest request) {

        String address = request.getHeader("x-forwarded-for");
        if (address == null || address.length() == 0) {
            address = request.getHeader("http-x-forwarded-for");
            if (address == null || address.length() == 0) {
                address = request.getHeader("remote-addr");
                if (address == null || address.length() == 0) {
                    address = request.getRemoteAddr();
                }
            }
        }
        logger.info("User has successfully read all roles from Host:"+ address +", Port:"+request.getRemotePort());

        return new ResponseEntity<>(roleMapper.ToDTO(roleService.getAll()), HttpStatus.OK);
    }
    @PreAuthorize("hasPermission(#id, 'Role', 'update')")
    @PostMapping
    public ResponseEntity<?> update(@RequestBody RoleDTO roleDTO, HttpServletRequest request) {
        roleService.update(roleMapper.ToModel(roleDTO));

        String address = request.getHeader("x-forwarded-for");
        if (address == null || address.length() == 0) {
            address = request.getHeader("http-x-forwarded-for");
            if (address == null || address.length() == 0) {
                address = request.getHeader("remote-addr");
                if (address == null || address.length() == 0) {
                    address = request.getRemoteAddr();
                }
            }
        }
        logger.info("User has successfully updated roles from Host:"+ address +", Port:"+request.getRemotePort());

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
