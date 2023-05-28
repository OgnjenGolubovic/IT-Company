package com.company.dto;

import com.company.model.SoftwareEngineerProject;

public class SoftwareEngineerProjectDTO {
    private Integer softwareEngineer;
    private Integer project;

    public SoftwareEngineerProjectDTO() { super(); }

    public Integer getSoftwareEngineer() {
        return softwareEngineer;
    }

    public void setSoftwareEngineer(Integer softwareEngineer) {
        this.softwareEngineer = softwareEngineer;
    }

    public Integer getProject() {
        return project;
    }

    public void setProject(Integer project) {
        this.project = project;
    }
}
