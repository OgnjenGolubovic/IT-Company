package com.company.dto;

import com.company.model.Project;

public class ProjectDTO {

    private Integer id;
    private String name;
    private String duration;
    private Integer user_id;
    private String job_description;

    public ProjectDTO() { super(); }

    public ProjectDTO(Integer id, String name, String duration){
        super();
        this.name = name;
        this.duration = duration;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
