package com.icg.icgbackend.repository;

import com.icg.icgbackend.model.EcoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcoCodeRepository extends JpaRepository<EcoCode,Long> {
}
