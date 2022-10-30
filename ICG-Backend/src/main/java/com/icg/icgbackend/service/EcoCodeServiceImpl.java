package com.icg.icgbackend.service;

import com.icg.icgbackend.dto.EcoCodeDto;
import com.icg.icgbackend.mapper.EcoCodeDtoMapper;
import com.icg.icgbackend.model.EcoCode;
import com.icg.icgbackend.repository.EcoCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EcoCodeServiceImpl implements EcoCodeService{

    private final EcoCodeDtoMapper ecoCodeDtoMapper;
    private final EcoCodeRepository ecoCodeRepository;
    @Override
    @Transactional
    public List<EcoCodeDto> findAllEcoCodes() {
        final List<EcoCode> ecoCodes = ecoCodeRepository.findAll();
        return ecoCodes.stream()
                .map(ecoCodeDtoMapper::mapper)
                .collect(Collectors.toList());
    }
}
