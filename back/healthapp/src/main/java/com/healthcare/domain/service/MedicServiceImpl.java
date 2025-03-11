package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.MedicRequest;
import com.healthcare.domain.dto.request.MedicRequestUpdate;
import com.healthcare.domain.dto.response.MedicResponse;
import com.healthcare.domain.exceptions.*;
import com.healthcare.domain.model.entity.Medic;
import com.healthcare.domain.repository.AppointmentRepository;
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
    private final AppointmentRepository appointmentRepository;
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

        List<MedicResponse> medicDTOS = medics.stream()
                .map(medicEntity -> modelMapper.map(medicEntity, MedicResponse.class))
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
        MedicResponse dto = modelMapper.map(medic, MedicResponse.class);
        return ResponseEntity.ok(Map.of("medic", dto));
    }

    @Override
    @Transactional
    public ResponseEntity<?> createMedic(MedicRequest medicRequest) {
        if (medicRequest == null) {
            throw new InvalidDataException("El cuerpo de la solicitud es obligatorio");
        }

        if (medicRepository.existsByDocumentId(medicRequest.getDocumentId())) {
            throw new DuplicatedEntryEx("Médico ya registrado");
        }

        Medic medic = new Medic(medicRequest);
        medicRepository.save(medic);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Médico creado con éxito",
                "medic", medicRequest));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteMedic(Long id) {
        Medic medic = medicRepository.findById(id)
                .orElseThrow(() -> new MedicNotFoundException("Médico no encontrado"));

        if (appointmentRepository.existsByMedicId(id)) {
            throw new MedicDeletionException("No se puede eliminar el Médico porque tiene citas asociadas");
        }

        medicRepository.delete(medic);
        return ResponseEntity.ok(Map.of("message", "Médico eliminado con éxito"));
    }
    
    @Transactional
    @Override
    public void edit(Long id, MedicRequestUpdate medicRequest) {
        var medic = medicRepository.findById(id)
                .orElseThrow(() -> new NotFoundInDatabaseException("No se encontró el médico"));
        modelMapper.map(medicRequest, medic);
        medicRepository.save(medic);
    }
}