package com.healthcare.domain.service;

import com.healthcare.domain.dto.PatientDTO;
import com.healthcare.domain.exceptions.NotFoundInDatabaseException;
import com.healthcare.domain.exceptions.PatientNotFoundException;
import com.healthcare.domain.model.entity.Patient;
import com.healthcare.domain.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements IPatientService{

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();

        List<PatientDTO> patientDTOS = patients.stream()
                .map(patientEntity -> modelMapper.map(patientEntity, PatientDTO.class))
                .toList();

        if (patientDTOS.isEmpty()) {
            throw new NotFoundInDatabaseException("Pacientes no encontrados");
        }

        return ResponseEntity.ok(Map.of("patients", patientDTOS));
    }

    @Override
    public ResponseEntity<?> getPatientById(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Paciente no encontrado"));
        PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
        return ResponseEntity.ok(Map.of("patient", patientDTO));
    }

    @Override
    @Transactional
    public ResponseEntity<?> createPatient(PatientDTO patientDTO){
        if(patientDTO == null){
            throw new RuntimeException("Required body");
        }
        Patient patient = new Patient(patientDTO);
        patientRepository.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Paciente creado con éxito",
                "patient", patientDTO
        ));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deletePatient(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Paciente no encontrado"));
        patientRepository.delete(patient);
        return ResponseEntity.ok(Map.of("message", "Paciente eliminado con éxito"));
    }
}