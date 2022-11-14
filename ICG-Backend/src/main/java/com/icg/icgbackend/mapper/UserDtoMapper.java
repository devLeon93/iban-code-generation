package com.icg.icgbackend.mapper;

import com.icg.icgbackend.dto.UserDto;
import com.icg.icgbackend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoMapper {

    public UserDto mapper(User user){

        final UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRoles());

        return userDto;

    }

}
