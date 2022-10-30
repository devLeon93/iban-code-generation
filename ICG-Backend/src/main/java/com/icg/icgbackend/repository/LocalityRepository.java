package com.icg.icgbackend.repository;

import com.icg.icgbackend.model.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalityRepository extends JpaRepository<Locality,Long> {

    @Query("SELECT l FROM Locality l WHERE l.localityCode = :cdc")
    List<Locality> findLocalitiesByLocalityCode(@Param("cdc") String cdc);

    @Query("SELECT l FROM Locality l  WHERE l.localityCode = l.parentLocalityCode")
    List<Locality> findRegions();

}
