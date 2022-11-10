package com.icg.icgbackend.service.Impl;

import com.icg.icgbackend.dto.IbanDto;
import com.icg.icgbackend.mapper.IbanDtoMapper;
import com.icg.icgbackend.model.Iban;
import com.icg.icgbackend.repository.IbanRepository;
import com.icg.icgbackend.service.IbanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author leonid.barsucovschi
 */

@Service
@RequiredArgsConstructor
public class IbanServiceImpl implements IbanService {

    private final IbanRepository ibanRepository;
    private final IbanDtoMapper ibanDtoMapper;

    @Override
    @Transactional
    public List<IbanDto> findAllIban() {
        final List<Iban> ibanDto = ibanRepository.findAll();
        return ibanDto.stream()
                .map(ibanDtoMapper::mapper)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Iban> findIbanCode(String ecdc, String lcdc, String plcdc) {
        var findIbanCode = ibanRepository.findIbanCode(ecdc, lcdc, plcdc);
        return findIbanCode;
    }
}

