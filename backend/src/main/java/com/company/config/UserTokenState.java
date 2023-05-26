package com.company.config;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenState {
	
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private Long expiresInRefresh;

    public UserTokenState() {
        this.accessToken = null;
        this.refreshToken = null;
        this.expiresIn = null;
        this.expiresInRefresh = null;

    }

    public UserTokenState(String accessToken, String refreshToken, long expiresIn, long expiresInRefresh) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.expiresInRefresh = expiresInRefresh;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getExpiresInRefresh() {
        return expiresInRefresh;
    }

    public void setExpiresInRefresh(Long expiresInRefresh) {
        this.expiresInRefresh = expiresInRefresh;
    }

}