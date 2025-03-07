package com.healthcare.domain.service;

import com.healthcare.domain.dto.PatientDTO;
import org.springframework.http.ResponseEntity;

public interface IPatientService {
    ResponseEntity<?> getAllPatients();
    ResponseEntity<?> getPatientById(Long id);
    ResponseEntity<?> createPatient(PatientDTO patientDTO);
    ResponseEntity<?> deletePatient(Long id);
}