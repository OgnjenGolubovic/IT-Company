package com.company.controller;

import com.company.dto.ProjectDTO;
import com.company.dto.SoftwareEngineerDTO;
import com.company.dto.SoftwareEngineerProjectDTO;
import com.company.dto.UserDTO;
import com.company.mapper.ProjectMapper;
import com.company.model.SoftwareEngineerProject;
import com.company.service.ProjectService;
import com.company.service.SoftwareEngineerProjectService;
import com.company.service.SoftwareEngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping(value = "/softwareEngineer", produces = MediaType.APPLICATION_JSON_VALUE)
public class SoftwareEngineerController {

    @Autowired
    private SoftwareEngineerService softwareEngineerService;
    @Autowired
    private SoftwareEngineerProjectService softwareEngineerProjectService;
    @Autowired
    private ProjectMapper projectMapper;
    @PreAuthorize("hasPermission(#id, 'SoftwareEngineer', 'update')")
    @PutMapping(value = "/profileUpdate", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<SoftwareEngineerDTO> updateProfile(@RequestBody SoftwareEngineerDTO softwareEngineerDTO){

        this.softwareEngineerService.updateProfile(softwareEngineerDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasPermission(#id, 'SoftwareEngineer', 'update')")
    @PutMapping(value = "/projectUpdate", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO){

        this.softwareEngineerProjectService.updateProject(projectDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasPermission(#id, 'Project', 'read')")
    @GetMapping(value = "/projects/{id}")
    public List<ProjectDTO> getProjects(@PathVariable Integer id){

        List<ProjectDTO> projectsDTO;

        List<SoftwareEngineerProject> sep = this.softwareEngineerProjectService.getAllById(id);

        projectsDTO = this.projectMapper.ToDTO(sep);

        return projectsDTO;
    }
    @PreAuthorize("hasPermission(#id, 'SoftwareEngineer', 'read')")
    @GetMapping(value = "/all")
    public ResponseEntity<List<SoftwareEngineerDTO>> getAllUsers(HttpServletRequest request){
        List<SoftwareEngineerDTO> users = this.softwareEngineerService.getAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PreAuthorize("hasPermission(#id, 'Project', 'create')")
    @PostMapping(value = "/assign")
    public ResponseEntity<SoftwareEngineerProjectDTO> assign(@RequestBody SoftwareEngineerProjectDTO softwareEngineerProjectDTO){
        softwareEngineerProjectService.assign(softwareEngineerProjectDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasPermission(#id, 'SoftwareEngineer', 'read')")
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<List<SoftwareEngineerDTO>> getUsersById(@PathVariable Integer id){

        List<Integer> usersIds = this.softwareEngineerProjectService.getUsersIdsByProjectsId(id);

        List<SoftwareEngineerDTO> users = this.softwareEngineerService.findByIds(usersIds);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }



}
