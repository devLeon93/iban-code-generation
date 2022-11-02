package com.icg.icgbackend.mapper;

import com.icg.icgbackend.dto.IbanDto;
import com.icg.icgbackend.dto.LocalityDto;
import com.icg.icgbackend.model.Iban;
import org.springframework.stereotype.Component;

/**
 * @author leonid.barsucovschi
 */

@Component
public class IbanDtoMapper {

    public IbanDto mapper(Iban iban){

        final IbanDto ibanDto = new IbanDto();

        ibanDto.setCodeEco(iban.getCodeEco());
        ibanDto.setParentLocalityCode(iban.getParentLocalityCode());
        ibanDto.setBudgetYear(iban.getBudgetYear());
        ibanDto.setIban(iban.getIban());
        return ibanDto;

    }
}
