package com.company.service;

import com.company.dto.ProjectDTO;
import com.company.model.Project;
import com.company.model.SoftwareEngineer;
import com.company.repository.ProjectRepository;
import com.company.repository.SoftwareEngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SoftwareEngineerRepository softwareEngineerRepository;

    public List<ProjectDTO> getById(Integer id) {

    List<ProjectDTO> projects = new ArrayList<>();

    List<Project> temps = this.projectRepository.findAll();

    //prolazimo kroz sve projekte u bazi i trazimo projekte na kom radi nas ulogovani korisnik
    for (Project project : temps){
//        for(SoftwareEngineer se : project.getSoftwareEngineers()){
//            if(se.getId() == id){
//
//            }
//        }
    }


    return projects;

    }





}
