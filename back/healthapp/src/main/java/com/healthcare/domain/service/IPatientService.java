package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.PatientRequestUpdate;

import jakarta.mail.MessagingException;

import org.springframework.http.ResponseEntity;

import com.healthcare.domain.dto.request.PatientRequest;

public interface IPatientService {
    ResponseEntity<?> getAllPatients();
    ResponseEntity<?> getPatientById(Long id);
    ResponseEntity<?> createPatient(PatientRequest patientDTO) throws MessagingException;
    ResponseEntity<?> deletePatient(Long id);
    void edit(Long id, PatientRequestUpdate patientRequest);
}