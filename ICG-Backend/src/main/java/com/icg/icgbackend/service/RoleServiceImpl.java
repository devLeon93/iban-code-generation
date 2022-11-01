package com.icg.icgbackend.service;

import com.icg.icgbackend.model.Role;
import com.icg.icgbackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author leonid.barsucovschi
 */

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
