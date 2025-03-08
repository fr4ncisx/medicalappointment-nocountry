package com.healthcare.domain.service;

import org.springframework.http.ResponseEntity;

import com.healthcare.domain.dto.request.PatientRequestDTO;

public interface IPatientService {
    ResponseEntity<?> getAllPatients();
    ResponseEntity<?> getPatientById(Long id);
    ResponseEntity<?> createPatient(PatientRequestDTO patientDTO);
    ResponseEntity<?> deletePatient(Long id);
}