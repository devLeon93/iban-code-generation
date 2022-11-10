package com.icg.icgbackend.service;

import com.icg.icgbackend.payload.reponse.JwtResponse;
import com.icg.icgbackend.payload.request.LoginRequest;
import com.icg.icgbackend.payload.request.SignupRequest;


public interface AuthService {

    void registerUser (SignupRequest signupRequest);

    JwtResponse authenticateUser(LoginRequest loginRequest);
}
