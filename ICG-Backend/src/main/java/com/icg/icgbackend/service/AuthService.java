package com.icg.icgbackend.service;

import com.icg.icgbackend.dto.LoginRequest;
import com.icg.icgbackend.dto.SignupRequest;
import com.icg.icgbackend.dto.UserDto;
import com.icg.icgbackend.util.JwtResponse;

public interface AuthService {

    void registerUser (SignupRequest signupRequest);

    JwtResponse authenticateUser(LoginRequest loginRequest);
}
