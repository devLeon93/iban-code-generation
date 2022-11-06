package com.icg.icgbackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/operator")
    @PreAuthorize("hasRole('OPERATOR') or hasRole('OPERATOR_RAION') or hasRole('ADMIN')")
    public String userAccess() {
        return "Operator Content.";
    }

    @GetMapping("/operator-raion")
    @PreAuthorize("hasRole('OPERATOR_RAION')")
    public String moderatorAccess() {
        return "Operator Raion.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
