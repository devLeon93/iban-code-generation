package com.icg.icgbackend.mapper;

import com.icg.icgbackend.dto.RoleDto;
import com.icg.icgbackend.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoMapper {

    public RoleDto mapper(Role role){
        final RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        return roleDto;
    }
}
