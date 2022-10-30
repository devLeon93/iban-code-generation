package com.icg.icgbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.icg.icgbackend.model.User;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String username;
    private String email;
    public static UserDto fromUser(User user) {
        UserDto userView = new UserDto();
        userView.setUsername(user.getUsername());
        userView.setEmail(user.getEmail());
        return userView;
    }
}
