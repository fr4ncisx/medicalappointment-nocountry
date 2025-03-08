package com.healthcare.domain.controller;

import com.healthcare.domain.dto.PatientRequestDTO;
import com.healthcare.domain.service.IPatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final IPatientService patientService;

    @PreAuthorize("hasAnyRole({'ADMIN', 'MEDICO'})")
    @GetMapping
    public ResponseEntity<?> getAllPatients(){
        return patientService.getAllPatients();
    }
    
    @PreAuthorize("hasAnyRole({'ADMIN', 'PACIENTE'})")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }
    
    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody @Valid PatientRequestDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    @PreAuthorize("hasAnyRole({'ADMIN','MEDICO'})")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id);
    }
}