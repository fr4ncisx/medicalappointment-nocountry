package com.healthcare.domain.service;

import com.healthcare.domain.dto.PatientRequestDTO;
import org.springframework.http.ResponseEntity;

public interface IPatientService {
    ResponseEntity<?> getAllPatients();
    ResponseEntity<?> getPatientById(Long id);
    ResponseEntity<?> createPatient(PatientRequestDTO patientDTO);
    ResponseEntity<?> deletePatient(Long id);
}