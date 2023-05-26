package com.company.controller;

import com.company.dto.RegisterRequestDTO;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private AdministratorService administratorService;

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


}
