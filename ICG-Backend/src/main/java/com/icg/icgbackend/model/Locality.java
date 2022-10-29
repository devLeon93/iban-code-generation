package com.icg.icgbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "locality")
public class Locality  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cdc")
    private String localityCode;

    @Column(name = "parent_cdc")
    private String parentLocalityCode;

    @Column(name = "name",nullable = false)
    private String localityTitle;
}
