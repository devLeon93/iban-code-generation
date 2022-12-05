package com.icg.icgbackend.controller;

import com.icg.icgbackend.dto.EcoCodeDto;
import com.icg.icgbackend.service.EcoCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/eco")
@RequiredArgsConstructor
public class EcoCodeController {

    private final EcoCodeService ecoCodeService;


    @GetMapping
    public ResponseEntity<List<EcoCodeDto>> getAllEco(){
        List<EcoCodeDto> ecoCodeDTOS = ecoCodeService.findAllEcoCodes();
        return new ResponseEntity<>(ecoCodeDTOS, HttpStatus.OK);
    }

}
