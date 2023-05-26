package com.company.dto;

public class SecurityCodeDTO {
    public String username;
    public String securityCode;

    public SecurityCodeDTO(String username, String securityCode) {
        this.username = username;
        this.securityCode = securityCode;
    }

    public SecurityCodeDTO() {
    }
}
