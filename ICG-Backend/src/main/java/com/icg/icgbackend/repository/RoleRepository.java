package com.icg.icgbackend.repository;

import com.icg.icgbackend.model.Role;
import com.icg.icgbackend.model.URole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(URole name);
}




