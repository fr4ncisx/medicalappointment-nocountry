package com.healthcare.domain.service;

import com.healthcare.domain.dto.MedicDTO;
import com.healthcare.domain.exceptions.MedicNotFoundException;
import com.healthcare.domain.exceptions.NotFoundInDatabaseException;
import com.healthcare.domain.model.entity.Medic;
import com.healthcare.domain.repository.MedicRepository;
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
public class MedicServiceImpl implements IMedicService {


    private final MedicRepository medicRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> getAllMedics(String speciality, String gender, String state) {
        List<Medic> medics = medicRepository.findAll();

        if (speciality != null) {
            medics = medics.stream()
                    .filter(medic -> medic.getSpeciality().name().equalsIgnoreCase(speciality))
                    .toList();
        }
        if (gender != null) {
            medics = medics.stream()
                    .filter(medic -> medic.getGender().name().equalsIgnoreCase(gender))
                    .toList();
        }
        if (state != null) {
            medics = medics.stream()
                    .filter(medic -> medic.getState().equalsIgnoreCase(state))
                    .toList();
        }

        List<MedicDTO> medicDTOS = medics.stream()
                .map(medicEntity -> modelMapper.map(medicEntity, MedicDTO.class))
                .toList();

        if (medicDTOS.isEmpty()) {
            throw new NotFoundInDatabaseException("Médicos no encontrados");
        }

        return ResponseEntity.ok(Map.of("medics", medicDTOS));
    }

    @Override
    public ResponseEntity<?> getMedicById(Long id) {
        Medic medic = medicRepository.findById(id)
                .orElseThrow(() -> new MedicNotFoundException("Médico no encontrado"));
        MedicDTO dto = modelMapper.map(medic, MedicDTO.class);
        return ResponseEntity.ok(Map.of("medic", dto));
    }

    @Override
    @Transactional
    public ResponseEntity<?> createMedic(MedicDTO medicDTO) {
        if(medicDTO == null){
            throw new RuntimeException("Required body");
        }
        Medic medic = new Medic(medicDTO);
        medicRepository.save(medic);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Médico creado con éxito",
                "medic", medicDTO
        ));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteMedic(Long id) {
        Medic medic = medicRepository.findById(id)
                .orElseThrow(() -> new MedicNotFoundException("Médico no encontrado"));
        medicRepository.delete(medic);
        return ResponseEntity.ok(Map.of("message", "Médico eliminado con éxito"));
    }
}