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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final static Logger logger = LogManager.getLogger(AuthenticationController.class);
    @PreAuthorize("hasPermission(#id, 'SoftwareEngineer', 'update')")
    @PutMapping(value = "/profileUpdate", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<SoftwareEngineerDTO> updateProfile(@RequestBody SoftwareEngineerDTO softwareEngineerDTO, HttpServletRequest request){

        this.softwareEngineerService.updateProfile(softwareEngineerDTO);

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
        logger.info("Software Engineer successfully updated profile info from Host:"+ address +", Port:"+request.getRemotePort());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasPermission(#id, 'SoftwareEngineer', 'update')")
    @PutMapping(value = "/projectUpdate", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO, HttpServletRequest request){

        this.softwareEngineerProjectService.updateProject(projectDTO);

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
        logger.info("User successfully updated project from Host:"+ address +", Port:"+request.getRemotePort());

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
        logger.info("User has successfully read all Software Engineers from Host:"+ address +", Port:"+request.getRemotePort());

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
    public ResponseEntity<List<SoftwareEngineerDTO>> getUsersById(@PathVariable Integer id, HttpServletRequest request){

        List<Integer> usersIds = this.softwareEngineerProjectService.getUsersIdsByProjectsId(id);

        List<SoftwareEngineerDTO> users = this.softwareEngineerService.findByIds(usersIds);

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
        logger.info("User successfully read Users by Id from Host:"+ address +", Port:"+request.getRemotePort());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }



}
