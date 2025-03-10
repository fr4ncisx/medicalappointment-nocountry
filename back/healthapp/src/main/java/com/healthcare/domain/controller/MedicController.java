package com.healthcare.domain.controller;

import com.healthcare.domain.dto.request.MedicRequest;
import com.healthcare.domain.service.IMedicService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/medic")
public class MedicController {

    private final IMedicService medicService;

    @GetMapping
    public ResponseEntity<?> getAllMedics(
            @RequestParam(required = false) String speciality,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String state) {
        return medicService.getAllMedics(speciality, gender, state);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicById(@PathVariable Long id) {
        return medicService.getMedicById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createMedic(@RequestBody @Valid MedicRequest medicRequest) {
        return medicService.createMedic(medicRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedic(@PathVariable Long id) {
        return medicService.deleteMedic(id);
    }
}