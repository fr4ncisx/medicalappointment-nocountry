package com.healthcare.domain.controller;

import com.healthcare.domain.dto.MedicDTO;
import com.healthcare.domain.service.IMedicService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medics")
public class MedicController {

    @Autowired
    private IMedicService medicService;

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

    @PostMapping
    public ResponseEntity<?> createMedic(@RequestBody @Valid MedicDTO medicDTO) {
        return medicService.createMedic(medicDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedic(@PathVariable Long id) {
        return medicService.deleteMedic(id);
    }
}