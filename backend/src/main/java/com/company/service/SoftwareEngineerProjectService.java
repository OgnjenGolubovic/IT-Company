package com.company.service;

import com.company.model.SoftwareEngineerProject;
import com.company.repository.SoftwareEngineerProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SoftwareEngineerProjectService {

    @Autowired
    private SoftwareEngineerProjectRepository softwareEngineerProjectRepository;


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


}
