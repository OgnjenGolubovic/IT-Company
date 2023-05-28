package com.company.controller;

import com.company.dto.AdminDTO;
import com.company.config.TokenUtils;
import com.company.dto.PasswordDTO;
import com.company.dto.RegisterRequestDTO;
import com.company.dto.SoftwareEngineerDTO;
import com.company.dto.UserDataDTO;
import com.company.model.RegistrationRequest;
import com.company.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value = "/regRequestAll")
    public ResponseEntity<List<RegisterRequestDTO>> registerRequests(HttpServletRequest request) {

        List<RegisterRequestDTO> registerRequestDTO = administratorService.findAllPendingRequestsDTO();

        return new ResponseEntity<>(registerRequestDTO, HttpStatus.OK);

    }

    @PutMapping(value = "/regRequestApprove", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<RegisterRequestDTO> approveRegistration(@RequestBody RegisterRequestDTO registerRequestDTO){

        this.administratorService.approveRegistration(registerRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/regRequestCancel", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<RegisterRequestDTO> cancelRegistration(@RequestBody RegisterRequestDTO registerRequestDTO){

        this.administratorService.cancelRegistration(registerRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/profileUpdate", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<AdminDTO> updateProfile(@RequestBody AdminDTO adminDTO){

        this.administratorService.updateProfile(adminDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/changedPassword")
    @ResponseBody
    public ResponseEntity<?> CheckIfPasswordChanged(HttpServletRequest request){
        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        boolean flag = administratorService.checkIfPasswordChanged(username);
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }
    @PostMapping(value = "/changePassword")
    public ResponseEntity<?> ChangePassword(HttpServletRequest request, @RequestBody PasswordDTO passwordDTO){
        if(passwordDTO.password == null || passwordDTO.confirmPassword == null ||
         !passwordDTO.password.equals(passwordDTO.confirmPassword)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        administratorService.changePassword(username, passwordDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
