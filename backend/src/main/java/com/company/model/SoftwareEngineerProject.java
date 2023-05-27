package com.company.model;

import javax.persistence.*;

@Entity
@Table(name = "se_project")
public class SoftwareEngineerProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "software_engineer_id")
    private SoftwareEngineer softwareEngineer;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "job_description")
    private String jobDescription;

    public SoftwareEngineerProject () { super(); }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SoftwareEngineer getSoftwareEngineer() {
        return softwareEngineer;
    }

    public void setSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        this.softwareEngineer = softwareEngineer;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
