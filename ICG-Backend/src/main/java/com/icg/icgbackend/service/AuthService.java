package com.icg.icgbackend.service;

import com.icg.icgbackend.payload.reponse.JwtResponse;
import com.icg.icgbackend.payload.request.LoginRequest;



public interface AuthService {

    JwtResponse authenticateUser(LoginRequest loginRequest);
}
