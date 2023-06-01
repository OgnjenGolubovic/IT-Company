package com.company.service;

import com.company.dto.ProjectDTO;
import com.company.dto.SoftwareEngineerDTO;
import com.company.dto.SoftwareEngineerProjectDTO;
import com.company.model.SoftwareEngineerProject;
import com.company.repository.SoftwareEngineerProjectRepository;
import com.company.repository.SoftwareEngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SoftwareEngineerProjectService {

    @Autowired
    private SoftwareEngineerProjectRepository softwareEngineerProjectRepository;

    @Autowired
    private SoftwareEngineerRepository softwareEngineerRepository;


    public List<SoftwareEngineerProject> getAllById(Integer id){

        List<SoftwareEngineerProject> temp = this.softwareEngineerProjectRepository.findAll();

        List<SoftwareEngineerProject> ret = new ArrayList<>();

        for(SoftwareEngineerProject sep : temp){
            if(sep.getSoftwareEngineer().getId() == id){
                ret.add(sep);
            }
        }
        return ret;
    }


    public void updateProject(ProjectDTO projectDTO) {

        List<SoftwareEngineerProject> seps;
        seps = this.softwareEngineerProjectRepository.findAll();

        for(SoftwareEngineerProject sep: seps){

            if(sep.getSoftwareEngineer().getId() == projectDTO.getUser_id() && projectDTO.getName().equals(sep.getProject().getName())){
                sep.setJobDescription(projectDTO.getJob_description());
                this.softwareEngineerProjectRepository.save(sep);
            }

        }

    }


    public void assign(SoftwareEngineerProjectDTO softwareEngineerProjectDTO) {

        SoftwareEngineerProject sep = new SoftwareEngineerProject(softwareEngineerProjectDTO.getSoftwareEngineer(), softwareEngineerProjectDTO.getProject());
        this.softwareEngineerProjectRepository.save(sep);


    }

    public List<Integer> getUsersIdsByProjectsId(Integer id) {

        List<SoftwareEngineerProject> temp = softwareEngineerProjectRepository.findAll();
        List<Integer> ret = new ArrayList<>();

        for(SoftwareEngineerProject sep : temp){
            if(sep.getProject().getId() == id){
                ret.add(sep.getSoftwareEngineer().getId());
            }
        }
        return ret;
    }
}
