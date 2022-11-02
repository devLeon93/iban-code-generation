package com.icg.icgbackend.controller;

import com.icg.icgbackend.dto.EcoCodeDto;
import com.icg.icgbackend.dto.IbanDto;
import com.icg.icgbackend.dto.LocalityDto;
import com.icg.icgbackend.service.IbanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author leonid.barsucovschi
 */
@RestController
@RequestMapping("api/iban")
@RequiredArgsConstructor
public class IbanController {

    private final IbanService ibanService;


    @GetMapping
    public ResponseEntity<List<IbanDto>> getAllIban(){
        List<IbanDto> ibanDto = ibanService.findAllIban();
        return new ResponseEntity<>(ibanDto, HttpStatus.OK);
    }

    @GetMapping("/{ecdc}/{lcdc}/{plcdc}")
    public ResponseEntity<?> getIbanCode(@PathVariable("ecdc") String ecdc,
                                                     @PathVariable("lcdc") String lcdc,
                                                     @PathVariable("plcdc") String plcdc) {
        var iban = ibanService.findIbanCode(ecdc,lcdc,plcdc);
        return ResponseEntity.ok(iban.get());
    }


}
