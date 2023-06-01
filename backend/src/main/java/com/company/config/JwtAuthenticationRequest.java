package com.company.config;

// DTO za login
public class JwtAuthenticationRequest {
	
    private String username;
    private String password;
    private int code;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password, int code) {
        this.setUsername(username);
        this.setPassword(password);
        this.setCode(code);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
