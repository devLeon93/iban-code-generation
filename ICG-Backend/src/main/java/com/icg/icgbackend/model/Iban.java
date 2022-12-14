package com.icg.icgbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "iban")
public class Iban extends BaseEntity {


    @Column(name = "budget_year",nullable = false)
    private Integer budgetYear;

    @Column(name = "code",nullable = false)
    private String codeEco;

    @Column(name = "parent_cdc",nullable = false)
    private String parentLocalityCode;

    @Column(name = "iban",nullable = false)
    private String iban;

}
