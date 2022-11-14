package com.icg.icgbackend.payload.request;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {

    @NotEmpty(message = "Please enter name")
    @Size(min = 3, max = 20)
    private String username;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message = "Email should be valid")
    private String email;

    private Set<String> role;

   // private String role;

    @NotEmpty(message = "Password is required")
    @Size(min = 4, max = 40)
    private String password;

}

