package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import com.company.config.TokenUtils;
import com.company.dto.UserDataDTO;
import com.company.model.User;
import com.company.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping(value = "api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private HumanResourcesService humanResourcesService;
    @Autowired
    private ProjectManagerService projectManagerService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private SoftwareEngineerService softwareEngineerService;

    @GetMapping(value = "/data")
    public ResponseEntity<UserDataDTO> getUserData(HttpServletRequest request) {

        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        int id = humanResourcesService.findByUsername(username);
        if(id != 0){
            return new ResponseEntity<>(new UserDataDTO(id, 2),HttpStatus.OK);
        }
        id = softwareEngineerService.findByUsername(username);
        if(id != 0){
            return new ResponseEntity<>(new UserDataDTO(id, 1),HttpStatus.OK);
        }
        id = projectManagerService.findByUsername(username);
        if(id != 0){
            return new ResponseEntity<>(new UserDataDTO(id, 3),HttpStatus.OK);
        }
        id = administratorService.findByUsername(username);
        if(id != 0){
            return new ResponseEntity<>(new UserDataDTO(id, 4),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
