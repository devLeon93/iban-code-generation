package com.icg.icgbackend.service;

import com.icg.icgbackend.dto.EcoCodeDto;

import java.util.List;

public interface EcoCodeService {
    List<EcoCodeDto> findAllEcoCodes ();
}
