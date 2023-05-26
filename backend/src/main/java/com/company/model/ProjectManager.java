package com.company.model;

import com.company.model.enums.CompanyRole;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
@DiscriminatorValue("3")
public class ProjectManager extends User{

    public ProjectManager(){ super(); }

    public ProjectManager(String email, String password, String name, String surname, String state, String city, String street, String streetNumber, String phone, CompanyRole cr) {

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

    }
}
