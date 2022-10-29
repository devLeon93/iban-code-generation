package com.icg.icgbackend.repository;

import com.icg.icgbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User,Long> {

    Optional<User> findUserByEmail(String email);
}
