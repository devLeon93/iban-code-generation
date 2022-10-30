package com.icg.icgbackend.mapper;

import com.icg.icgbackend.dto.EcoCodeDto;
import com.icg.icgbackend.model.EcoCode;
import org.springframework.stereotype.Component;

@Component
public class EcoCodeDtoMapper {
    public EcoCodeDto mapper(EcoCode ecoCode){
        final EcoCodeDto ecoCodeDTO = new  EcoCodeDto();
        ecoCodeDTO.setCode(ecoCode.getCodeEco());
        ecoCodeDTO.setLabel(ecoCode.getLabelMD());
        return ecoCodeDTO;
    }
}
