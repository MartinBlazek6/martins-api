package com.martin.entity;

import com.martin.entity.records.UserRecord;
import lombok.*;

@Data
public class JwtResponse {

    private UserRecord user;

    private String jwtToken;

    public JwtResponse(UserRecord user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

}
