package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.LabResultsRequest;
import com.healthcare.domain.dto.response.LabResultsResponse;
import com.healthcare.domain.exceptions.NotFoundInDatabaseException;
import com.healthcare.domain.model.entity.LabResults;
import com.healthcare.domain.model.entity.Patient;
import com.healthcare.domain.repository.LabResultsRepository;
import com.healthcare.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LabResultsServiceImpl implements ILabResultsService {

    private final PatientRepository patientRepository;
    private final LabResultsRepository labResultsRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void assign(Long patientId, LabResultsRequest labResultsRequest) {
        var patient = getPatient(patientId);
        LabResults labResults = new LabResults(labResultsRequest, patient);
        patient.getLabResults().add(labResults);
        patientRepository.save(patient);
    }

    @Transactional
    @Override
    public void edit(Long patientId, Long labResultId, LabResultsRequest labResultsRequest) {
        var patient = getPatient(patientId);
        var labResult = getLabResult(labResultId);
        modelMapper.map(labResultsRequest, labResult);
        patient.getLabResults().add(labResult);
        patientRepository.save(patient);
    }

    @Transactional
    @Override
    public void delete(Long labResultId) {
        var labs = getLabResult(labResultId);
        labResultsRepository.delete(labs);
    }

    @Override
    public List<LabResultsResponse> getAll(Long patientId) {
        var patient = getPatient(patientId);
        var getResults = patient.getLabResults();
        if (!getResults.isEmpty()) {
            return getResults.stream()
                    .map(m -> modelMapper.map(m, LabResultsResponse.class))
                    .toList();
        }
        throw new NotFoundInDatabaseException("No hay resultados de laboratorio");
    }

    private Patient getPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundInDatabaseException("Paciente no encontrado"));
    }

    private LabResults getLabResult(Long id) {
        return labResultsRepository.findById(id)
                .orElseThrow(() -> new NotFoundInDatabaseException("Resultado de laboratorio no encontrado"));
    }
}
