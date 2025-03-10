package com.healthcare.domain.service;

import org.springframework.http.ResponseEntity;

import com.healthcare.domain.dto.request.MedicRequest;

public interface IMedicService {
    ResponseEntity<?> getAllMedics(String speciality, String gender, String state);
    ResponseEntity<?> getMedicById(Long id);
    ResponseEntity<?> createMedic(MedicRequest medicRequest);
    ResponseEntity<?> deleteMedic(Long id);
}