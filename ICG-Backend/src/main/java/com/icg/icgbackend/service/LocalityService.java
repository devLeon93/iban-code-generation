package com.icg.icgbackend.service;

import com.icg.icgbackend.dto.LocalityDto;

import java.util.List;

public interface LocalityService {

    List<LocalityDto> findAllLocalities();

    List<LocalityDto> findLocalityByCode(String cdc);

    List<LocalityDto> findRegions();
}
