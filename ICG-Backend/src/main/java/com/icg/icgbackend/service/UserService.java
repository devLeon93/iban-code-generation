package com.icg.icgbackend.service;

import com.icg.icgbackend.dto.RoleDto;
import com.icg.icgbackend.dto.UserDto;
import com.icg.icgbackend.payload.request.SignupRequest;

import java.util.List;

public interface UserService {

    void createUser(SignupRequest signupRequest);

    void editUser(Long id,SignupRequest signupRequest);

    void deleteUser(Long id,SignupRequest signupRequest);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    List<RoleDto> getAllRoles();

}
