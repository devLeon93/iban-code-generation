package com.icg.icgbackend.util;

import lombok.Data;
@Data
public class JWTSuccessAuthenticateResponse {
    private String token;
    public JWTSuccessAuthenticateResponse(String token) {
        this.token = token;
    }
}
