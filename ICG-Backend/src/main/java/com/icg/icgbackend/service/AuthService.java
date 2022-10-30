package com.icg.icgbackend.service;

import com.icg.icgbackend.dto.LoginRequest;
import com.icg.icgbackend.dto.RegisterRequest;
import com.icg.icgbackend.dto.UserDto;
import com.icg.icgbackend.util.JWTSuccessAuthenticateResponse;

public interface AuthService {

    UserDto registerUser (RegisterRequest registerUser);

    JWTSuccessAuthenticateResponse authenticateUser(LoginRequest authenticateUser);
}
