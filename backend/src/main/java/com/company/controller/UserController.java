package com.company.controller;


import com.company.config.PermissionUtil;
import com.company.config.TokenUtils;
import com.company.config.TwoFactorAuthenticator;
import com.company.dto.QrCodeDTO;
import com.company.dto.UserDTO;
import com.company.dto.UserDataDTO;
import com.company.model.User;
import com.company.service.*;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value = "users")
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
    @Autowired
    private TwoFactorAuthenticator twoFactorAuthenticator;
    @Autowired
    private PermissionUtil permissionUtil;

    @GetMapping(value = "/data")
    public ResponseEntity<UserDataDTO> getUserData(HttpServletRequest request) {
       permissionUtil.checkPermission("USERS_R", SecurityContextHolder.getContext().getAuthentication());
       String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
       int id = humanResourcesService.findByUsername(username);
       if (id != 0) {
           return new ResponseEntity<>(new UserDataDTO(id, 2), HttpStatus.OK);
       }
       id = softwareEngineerService.findByUsername(username);
       if (id != 0) {
           return new ResponseEntity<>(new UserDataDTO(id, 1), HttpStatus.OK);
       }
       id = projectManagerService.findByUsername(username);
       if (id != 0) {
           return new ResponseEntity<>(new UserDataDTO(id, 3), HttpStatus.OK);
       }
       id = administratorService.findByUsername(username);
       if (id != 0) {
           return new ResponseEntity<>(new UserDataDTO(id, 4), HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/qrcode")
    public ResponseEntity<QrCodeDTO> qrCodeGenerator(HttpServletRequest request) {
        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        GoogleAuthenticatorKey secretKey = twoFactorAuthenticator.generateSecretKey();
        userService.setSecretKeyByUsername(username, secretKey.getKey());
        return new ResponseEntity<QrCodeDTO>(new QrCodeDTO(secretKey.getKey()), HttpStatus.OK);
    }
    @GetMapping("/set2FA")
    public ResponseEntity<?> set2FA(HttpServletRequest request) {
        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        userService.setTFAByUsername(username);
        return ResponseEntity.ok("");
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        User user = userService.findById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(HttpServletRequest request){
        List<UserDTO> users = this.userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }






}
