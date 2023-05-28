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


    public List<ProjectDTO> getAll() {
        List<Project> temp = this.projectRepository.findAll();
        List<ProjectDTO> ret = new ArrayList<>();

        for(Project project : temp){
            ProjectDTO p = new ProjectDTO(project.getName(), project.getDuration());
            ret.add(p);
        }
        return ret;
    }


    public void createProject(ProjectDTO projectDTO) {
        Project p = new Project(projectDTO.getName(), projectDTO.getDuration());
        projectRepository.save(p);
    }
}
