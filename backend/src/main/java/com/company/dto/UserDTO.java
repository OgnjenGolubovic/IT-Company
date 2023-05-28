package com.company.dto;

public class UserDTO {

    private String name;
    private String surname;
    private String company_role;
    private String phone_number;

    public UserDTO() { super(); }

    public UserDTO(String name, String surname, String company_role, String phone_number) {
        this.name = name;
        this.surname = surname;
        this.company_role = company_role;
        this.phone_number = phone_number;
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

    public String getCompany_role() {
        return company_role;
    }

    public void setCompany_role(String company_role) {
        this.company_role = company_role;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
