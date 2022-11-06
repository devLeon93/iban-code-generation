package com.icg.icgbackend.controller;

import com.icg.icgbackend.dto.LoginRequest;
import com.icg.icgbackend.dto.SignupRequest;
import com.icg.icgbackend.service.AuthService;
import com.icg.icgbackend.util.ExceptionResponse;
import com.icg.icgbackend.util.JwtResponse;
import com.icg.icgbackend.util.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signupRequest) {
        authService.registerUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> customException(BadCredentialsException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
