package com.healthcare.domain.service;

import java.util.List;
import com.healthcare.domain.dto.request.MedicalRecordsRequest;
import com.healthcare.domain.dto.response.MedicalRecordsReponse;

public interface IMedicalRecordsService {
    public List<MedicalRecordsReponse> retrieveRecords(Long patientId, String token);
    public void createMedicalRecord(Long patientId, MedicalRecordsRequest request);
}
