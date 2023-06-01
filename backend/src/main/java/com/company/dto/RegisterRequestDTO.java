package com.company.dto;

import com.company.dto.enums.Status;
import com.company.model.enums.CompanyRole;

public class RegisterRequestDTO {
    private String email;
    private String name;
    private String surname;
    private String companyRole;
    private Status status;

    public RegisterRequestDTO(){ super(); }

    public RegisterRequestDTO(String email, String name, String surname, String companyRole, Status status) {
        super();
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.companyRole = companyRole;
        this.status = status;
    }

    public RegisterRequestDTO(String email, String name, String surname, CompanyRole companyRole) {
        super();
        this.email = email;
        this.name = name;
        this.surname = surname;

        if(companyRole.equals(CompanyRole.HR)){
            this.companyRole = "Human Resource Manager";
        } else if (companyRole.equals(CompanyRole.SOFTWARE_ENGINEER)) {
            this.companyRole = "Software Engineer";
        } else if (companyRole.equals(CompanyRole.PROJECT_MANAGER)) {
            this.companyRole = "Project Manager";
        } else{
            this.companyRole = "No company role";
        }

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

    public String getCompanyRole() {
        return companyRole;
    }

    public void setCompanyRole(String companyRole) {
        this.companyRole = companyRole;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
