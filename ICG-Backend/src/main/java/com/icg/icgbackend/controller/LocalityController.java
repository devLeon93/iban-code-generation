package com.icg.icgbackend.controller;

import com.icg.icgbackend.dto.LocalityDto;
import com.icg.icgbackend.model.Locality;
import com.icg.icgbackend.service.LocalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/locality")
@RequiredArgsConstructor
public class LocalityController {

    private final LocalityService localityService;

    @GetMapping
    public ResponseEntity<List<LocalityDto>> getAllLocalities() {
        List<LocalityDto> localityDTOList = localityService.findAllLocalities();
        return new ResponseEntity<>(localityDTOList, HttpStatus.OK);
    }

   /* @GetMapping("/regions")
    public ResponseEntity<List<LocalityDto>> getRegions(){
        List<LocalityDto> regions = localityService.findRegions();
        return new ResponseEntity<>(regions,HttpStatus.OK);
    }*/

    @GetMapping("/regions")
    public ResponseEntity<List<LocalityDto>> getRegions() {

        var map = new HashMap<String, LocalityDto>();
        localityService
                .findRegions()
                .forEach(item ->
                        map.put(item.getLocalityCode(), item));

        return new ResponseEntity<>(map.entrySet().stream()
                .map(item -> item.getValue())
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{cdc}")
    public ResponseEntity<List<LocalityDto>> getLocalitiesByCode(@PathVariable("cdc") String cdc) {
        List<LocalityDto> localities = localityService.findLocalityByCode(cdc);
        return new ResponseEntity<>(localities, HttpStatus.OK);
    }

}
