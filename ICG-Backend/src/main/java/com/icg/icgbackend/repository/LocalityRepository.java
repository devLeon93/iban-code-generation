package com.icg.icgbackend.repository;

import com.icg.icgbackend.model.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalityRepository extends JpaRepository<Locality,Long> {

}
