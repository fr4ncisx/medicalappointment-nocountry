package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.MedicationsRequestDTO;
import com.healthcare.domain.dto.response.MedicationsResponse;

import java.util.List;

public interface IMedicationService {
    public void assign(Long patientId, MedicationsRequestDTO medicationsRequest);
    public void delete(Long medicationId);
    public void edit(Long patientId, Long medicationId, MedicationsRequestDTO medicationsRequestDTO);
    public List<MedicationsResponse> getAll(Long patientId);
}
