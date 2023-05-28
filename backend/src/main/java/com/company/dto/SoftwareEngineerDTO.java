package com.company.dto;

import java.io.File;

public class SoftwareEngineerDTO {

    private Integer id;
    private String email;
    private String name;
    private String surname;
    private String state;
    private String city;
    private String street;
    private String streetNumber;
    private String phone;
    private String skills;
    private File cv;

    public SoftwareEngineerDTO(){ super(); }

    public SoftwareEngineerDTO(Integer id, String email, String name, String surname, String state, String city,
                               String street, String streetNumber, String phone, String skills, File cv) {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.state = state;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.phone = phone;
        this.skills = skills;
        this.cv = cv;
    }

    public SoftwareEngineerDTO(Integer id, String name, String surname) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public File getCv() {
        return cv;
    }

    public void setCv(File cv) {
        this.cv = cv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
