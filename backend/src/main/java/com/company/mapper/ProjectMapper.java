package com.company.mapper;

import com.company.dto.ProjectDTO;
import com.company.model.Project;
import com.company.model.SoftwareEngineerProject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectMapper  implements IMapper<SoftwareEngineerProject, ProjectDTO>{


    @Override
    public SoftwareEngineerProject ToModel(ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public List<SoftwareEngineerProject> ToModel(List<ProjectDTO> projectsDTO) {
        return null;
    }

    @Override
    public ProjectDTO ToDTO(SoftwareEngineerProject project) {
        return null;
    }

    @Override
    public List<ProjectDTO> ToDTO(List<SoftwareEngineerProject> projects) {

        List<ProjectDTO> projectsDTO = new ArrayList<>();

        for(SoftwareEngineerProject project : projects){
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setName(project.getProject().getName());
            projectDTO.setDuration(project.getProject().getDuration());
            projectDTO.setJob_description(project.getJobDescription());
            projectsDTO.add(projectDTO);
        }

        return projectsDTO;
    }
}
