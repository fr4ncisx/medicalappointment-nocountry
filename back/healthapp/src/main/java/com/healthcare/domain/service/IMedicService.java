package com.healthcare.domain.service;

import com.healthcare.domain.dto.MedicDTO;
import org.springframework.http.ResponseEntity;

public interface IMedicService {
    ResponseEntity<?> getAllMedics(String speciality, String gender, String state);
    ResponseEntity<?> getMedicById(Long id);
    ResponseEntity<?> createMedic(MedicDTO medicDTO);
    ResponseEntity<?> deleteMedic(Long id);
}