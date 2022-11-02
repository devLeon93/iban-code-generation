package com.icg.icgbackend.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author leonid.barsucovschi
 */
@Data
public class IbanDto {

    private Integer budgetYear;
    private String codeEco;
    private String parentLocalityCode;
    private String iban;
}
