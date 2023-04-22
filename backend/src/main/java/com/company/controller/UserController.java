package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import com.company.config.TokenUtils;
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

    @GetMapping(value = "/data")
    public ResponseEntity<?> getUserData(HttpServletRequest request) {

        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(username, HttpStatus.OK);
    }
}
