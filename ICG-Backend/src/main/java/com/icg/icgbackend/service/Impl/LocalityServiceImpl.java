package com.icg.icgbackend.service.Impl;

import com.icg.icgbackend.dto.LocalityDto;
import com.icg.icgbackend.mapper.LocalityDtoMapper;
import com.icg.icgbackend.model.Locality;
import com.icg.icgbackend.repository.LocalityRepository;
import com.icg.icgbackend.service.LocalityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepository localityRepository;
    private final LocalityDtoMapper localityDtoMapper;

    @Override
    @Transactional
    public List<LocalityDto> findAllLocalities() {
        final List<Locality> localityList = localityRepository.findAll();
        return localityList.stream()
                .map(localityDtoMapper::mapper)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<LocalityDto> findLocalityByCode(String cdc) {
        List<Locality> findLocalitiesByCode = localityRepository.findLocalitiesByLocalityCode(cdc);
        return findLocalitiesByCode.stream()
                .map(localityDtoMapper::mapper)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<LocalityDto> findRegions() {
        List<Locality> findRegionsList = localityRepository.findRegions();
        return findRegionsList.stream()
                .map(localityDtoMapper::mapper)
                .collect(Collectors.toList());

    }
}
