package com.company.model;

import com.company.model.enums.CompanyRole;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@SuppressWarnings("serial")
@DiscriminatorValue("1")
public class SoftwareEngineer extends User {

    @Column(nullable = true)
    private Calendar timeOfRegistration;

    @Column(nullable = true)
    private String skills;

//    @ManyToMany(mappedBy = "jobDescription")
//    private List<Project> projectsDescription;

    @ManyToMany(mappedBy = "softwareEngineer")
    private List<SoftwareEngineerProject> softwareEngineerProjects;

    public SoftwareEngineer (){ super(); }

    public SoftwareEngineer(String email, String password, String name, String surname, String state, String city,
                            String street, String streetNumber, String phone, CompanyRole cr, Calendar instance, List<Role> roles) {

        super();
        this.setUsername(email);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        this.setState(state);
        this.setCity(city);
        this.setStreet(street);
        this.setStreetNumber(streetNumber);
        this.setPhoneNumber(phone);
        this.setCompanyRole(cr);
        this.setTimeOfRegistration(instance);
        this.setSkills("");
        this.setRoles(roles);
    }

    public Calendar getTimeOfRegistration() {
        return timeOfRegistration;
    }

    public void setTimeOfRegistration(Calendar timeOfRegistration) {
        this.timeOfRegistration = timeOfRegistration;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }


}
