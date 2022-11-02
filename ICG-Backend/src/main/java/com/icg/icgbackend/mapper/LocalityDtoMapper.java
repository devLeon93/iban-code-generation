package com.icg.icgbackend.mapper;

import com.icg.icgbackend.dto.LocalityDto;
import com.icg.icgbackend.model.Locality;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class LocalityDtoMapper {

    public LocalityDto mapper(Locality locality){

        final LocalityDto localityDTO = new LocalityDto();
        localityDTO.setLocalityCode(locality.getLocalityCode());
        localityDTO.setParentLocalityCode(locality.getParentLocalityCode());
        localityDTO.setLocalityTitle(locality.getLocalityTitle());
        return localityDTO;
    }
}
