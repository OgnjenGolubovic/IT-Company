package com.company.controller;

import com.company.dto.ProjectDTO;
import com.company.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @GetMapping(value = "/all")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(HttpServletRequest request){
        List<ProjectDTO> projects = this.projectService.getAll();

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO){
        projectService.createProject(projectDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }





}
