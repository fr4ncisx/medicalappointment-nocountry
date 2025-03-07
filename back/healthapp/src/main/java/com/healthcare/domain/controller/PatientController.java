package com.healthcare.domain.controller;

import com.healthcare.domain.dto.PatientDTO;
import com.healthcare.domain.service.IPatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @GetMapping
    public ResponseEntity<?> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody @Valid PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id);
    }
}