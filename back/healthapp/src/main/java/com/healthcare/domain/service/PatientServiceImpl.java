package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.PatientRequestDTO;
import com.healthcare.domain.dto.request.UserRequestDTO;
import com.healthcare.domain.dto.response.PatientResponseDTO;
import com.healthcare.domain.exceptions.DuplicatedEntryEx;
import com.healthcare.domain.exceptions.InvalidDataException;
import com.healthcare.domain.exceptions.NotFoundInDatabaseException;
import com.healthcare.domain.exceptions.PatientNotFoundException;
import com.healthcare.domain.model.entity.Patient;
import com.healthcare.domain.model.entity.User;
import com.healthcare.domain.model.enums.Role;
import com.healthcare.domain.repository.PatientRepository;
import com.healthcare.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements IPatientService{

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> response = patients.stream()
                .map(patientEntity -> modelMapper.map(patientEntity, PatientResponseDTO.class))
                .toList();
        if (response.isEmpty()) {
            throw new NotFoundInDatabaseException("Pacientes no encontrados");
        }

        return ResponseEntity.ok(Map.of("patients", response));
    }

    @Override
    public ResponseEntity<?> getPatientById(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Paciente no encontrado"));
        var patientDTO = modelMapper.map(patient, PatientResponseDTO.class);
        return ResponseEntity.ok(Map.of("patient", patientDTO));
    }

    @Override
    @Transactional
    public ResponseEntity<?> createPatient(PatientRequestDTO patientDTO) {
        assertValidation(patientDTO);
        UserRequestDTO userDTO = patientDTO.getUser();        
        Patient patient = new Patient(patientDTO);
        User user = new User(userDTO, encodePassword(userDTO.getPassword()), Role.PACIENTE);
        userRepository.save(user);
        patient.setUser(user);
        patientRepository.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Paciente creado con éxito",
                "patient", patientDTO));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deletePatient(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Paciente no encontrado"));
        patientRepository.delete(patient);
        return ResponseEntity.ok(Map.of("message", "Paciente eliminado con éxito"));
    }

    private void assertValidation(PatientRequestDTO patientDTO) {
        if (patientDTO == null) {
            throw new InvalidDataException("El cuerpo de la solicitud es obligatorio");
        }
        if (patientRepository.existsByDocumentId(patientDTO.getDocumentId())) {
            throw new DuplicatedEntryEx("Paciente ya registrado");
        }
        if (userRepository.existsByEmail(patientDTO.getUser().getEmail())) {
            throw new DuplicatedEntryEx("El correo ya esta asociado a una cuenta");
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}