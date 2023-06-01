package com.company.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "projects")
@SuppressWarnings("serial")
public class Project  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String duration;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "se_project",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "software_engineer_id", referencedColumnName = "id"),
            })
    private List<SoftwareEngineerProject> softwareEngineerProjects;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "se_project",
//            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
//            inverseJoinColumns = {
//                    @JoinColumn(name = "job_description", referencedColumnName = "job_description")
//            })
//    private List<SoftwareEngineer> jobDescription;

    public Project(){ super(); }

    public Project(String name, String duration) {
        this.name = name;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
