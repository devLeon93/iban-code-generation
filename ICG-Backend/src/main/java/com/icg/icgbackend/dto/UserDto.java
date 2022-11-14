package com.icg.icgbackend.dto;

import com.icg.icgbackend.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private Set<Role> role;

}
