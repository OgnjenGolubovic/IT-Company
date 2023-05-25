package com.company.model;

import com.company.model.enums.CompanyRole;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RegistrationRequests")
@SuppressWarnings("serial")
@DiscriminatorColumn(name="request", discriminatorType = DiscriminatorType.STRING)
public class RegistrationRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String surname;

    @Column(nullable = true)
    private CompanyRole companyRole;

    public RegistrationRequest(){
        super();
    }

    public RegistrationRequest(String email, String name, String surname, CompanyRole companyRole) {
        super();
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.companyRole = companyRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public CompanyRole getCompanyRole() {
        return companyRole;
    }

    public void setCompanyRole(CompanyRole companyRole) {
        this.companyRole = companyRole;
    }
}
