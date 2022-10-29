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

    @Column(name = "code",nullable = false)
    private String codeEco;

    @Column(name = "cdc",nullable = false)
    private String localityCode;

    @Column(name = "iban",nullable = false)
    private String iban;

    @OneToOne(fetch = FetchType.LAZY)
    private EcoCode ecoCode;

    @OneToOne(fetch = FetchType.LAZY)
    private Locality locality;

}
