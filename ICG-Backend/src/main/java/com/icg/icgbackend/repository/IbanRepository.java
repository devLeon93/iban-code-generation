package com.icg.icgbackend.repository;

import com.icg.icgbackend.model.Iban;
import com.icg.icgbackend.model.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * @author leonid.barsucovschi
 */

@Repository
public interface IbanRepository extends JpaRepository<Iban,Long> {

    @Query("SELECT i FROM Iban i " +
            "INNER JOIN EcoCode ec on i.codeEco = ec.codeEco " +
            "INNER JOIN Locality l on i.parentLocalityCode = l.parentLocalityCode " +
            "WHERE ec.codeEco = :ecdc and l.localityCode = :lcdc and l.parentLocalityCode = :plcdc")
    Optional<Iban> findIbanCode(@Param("ecdc") String ecdc,
                                @Param("lcdc") String lcdc,
                                @Param("plcdc") String plcdc);


}
