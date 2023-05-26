package com.company.controller;

import com.company.dto.RegisterRequestDTO;
import com.company.dto.SoftwareEngineerDTO;
import com.company.model.SoftwareEngineer;
import com.company.model.User;
import com.company.service.SoftwareEngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/softwareEngineer", produces = MediaType.APPLICATION_JSON_VALUE)
public class SoftwareEngineerController {

    @Autowired
    private SoftwareEngineerService softwareEngineerService;

    @PutMapping(value = "/profileUpdate", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<SoftwareEngineerDTO> updateProfile(@RequestBody SoftwareEngineerDTO softwareEngineerDTO){

        this.softwareEngineerService.updateProfile(softwareEngineerDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }







}
