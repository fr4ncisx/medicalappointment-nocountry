package com.healthcare.domain.service;

import org.springframework.http.ResponseEntity;

import com.healthcare.domain.dto.request.AppointmentRequest;

public interface IAppointmentService {
    ResponseEntity<?> scheduleAppointment(Long patientId, Long medicId, AppointmentRequest appointmentRequest);
    ResponseEntity<?> updateAppointment(Long appointmentId, AppointmentRequest appointmentRequest);
    ResponseEntity<?> cancelAppointment(Long appointmentId);
    ResponseEntity<?> getAppointmentsByPatient(Long patientId);
}