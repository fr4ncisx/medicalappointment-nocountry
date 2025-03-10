package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.MedicationsRequestDTO;
import com.healthcare.domain.dto.response.MedicationsResponseDTO;
import com.healthcare.domain.exceptions.NotFoundInDatabaseException;
import com.healthcare.domain.model.entity.Medications;
import com.healthcare.domain.model.entity.Patient;
import com.healthcare.domain.repository.MedicationRepository;
import com.healthcare.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MedicationServiceImpl implements IMedicationService {

    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void assign(Long patientId, MedicationsRequestDTO medicationsRequest) {
        var patient = getPatient(patientId);
        var meds = new Medications(medicationsRequest, patient);
        patient.getMedications().add(meds);
        patientRepository.save(patient);
    }

    @Transactional
    @Override
    public void edit(Long patientId, Long medicationId, MedicationsRequestDTO medicationsRequestDTO) {
        var patient = getPatient(patientId);
        var meds = getMedication(medicationId);
        modelMapper.map(medicationsRequestDTO, meds);
        patient.getMedications().add(meds);
        meds.setPatient(patient);
        patientRepository.save(patient);
    }

    @Transactional
    @Override
    public void delete(Long medicationId) {
        Medications meds = getMedication(medicationId);
        medicationRepository.delete(meds);
    }

    @Override
    public List<MedicationsResponseDTO> getAll(Long patientId) {
        var patient = getPatient(patientId);
        return patient.getMedications().stream()
                .map(m -> modelMapper.map(m, MedicationsResponseDTO.class))
                .toList();
    }

    private Patient getPatient(Long id) {
        var patientOpt = patientRepository.findById(id);
        return patientOpt.orElseThrow(() -> new NotFoundInDatabaseException("Paciente no encontrado"));
    }

    private Medications getMedication(Long id) {
        var medsOpt = medicationRepository.findById(id);
        return medsOpt.orElseThrow(() -> new NotFoundInDatabaseException("Medicación no encontrada"));
    }

}
