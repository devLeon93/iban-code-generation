package com.icg.icgbackend.service;

import com.icg.icgbackend.dto.IbanDto;
import com.icg.icgbackend.model.Iban;

import java.util.List;
import java.util.Optional;

/**
 * @author leonid.barsucovschi
 */
public interface IbanService {

    List<IbanDto> findAllIban();

    Optional<Iban> findIbanCode(String ecdc, String lcdc, String plcdc);

}
