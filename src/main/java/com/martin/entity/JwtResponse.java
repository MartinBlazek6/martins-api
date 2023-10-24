package com.martin.entity;

import com.martin.entity.records.UserRecord;

public class JwtResponse {

//    private User user;
    private UserRecord user;
    private String jwtToken;

    public JwtResponse(UserRecord user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public UserRecord getUser() {
        return user;
    }

    public void setUser(UserRecord user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
