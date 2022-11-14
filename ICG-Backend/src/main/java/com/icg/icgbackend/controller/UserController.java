package com.icg.icgbackend.controller;

import com.icg.icgbackend.dto.RoleDto;
import com.icg.icgbackend.dto.UserDto;
import com.icg.icgbackend.model.User;
import com.icg.icgbackend.payload.request.SignupRequest;
import com.icg.icgbackend.repository.UserRepository;
import com.icg.icgbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;
    @PostMapping
    public ResponseEntity<?> newUser(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            userService.createUser(signupRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok( userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

/*
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok( userRepository.findAll());
    }*/

/*    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userRepository.findById(id).get());
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody SignupRequest signupRequest,
                                        @PathVariable Long id) {

        var user = userRepository.findById(id).get();
        user.setEmail(signupRequest.getEmail());
        user.setUsername(signupRequest.getUsername());
        return ResponseEntity.ok( userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, SignupRequest signupRequest) {
        userService.deleteUser(id, signupRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roles = userService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}
