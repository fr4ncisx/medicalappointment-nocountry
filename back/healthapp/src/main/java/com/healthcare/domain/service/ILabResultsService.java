package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.LabResultsRequest;
import com.healthcare.domain.dto.response.LabResultsResponse;

import java.util.List;

public interface ILabResultsService {
    public void assign(Long patientId, LabResultsRequest labResultsRequest);
    public void edit(Long patientId, Long labResultId, LabResultsRequest labResultsRequest);
    public void delete(Long labResultId);
    public List<LabResultsResponse> getAll(Long patientId);

}
