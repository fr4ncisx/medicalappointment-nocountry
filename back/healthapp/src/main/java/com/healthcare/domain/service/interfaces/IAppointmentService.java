package com.healthcare.domain.service.interfaces;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import com.healthcare.domain.dto.request.AppointmentRequest;

public interface IAppointmentService {
    ResponseEntity<?> scheduleAppointment(Long patientId, Long medicId, AppointmentRequest appointmentRequest) throws MessagingException;

    ResponseEntity<?> updateAppointment(Long appointmentId, AppointmentRequest appointmentRequest);

    ResponseEntity<?> cancelAppointment(Long appointmentId) throws MessagingException;

    ResponseEntity<?> getAppointmentsByPatient(Long patientId);

    ResponseEntity<?> getAppointmentsByMedic(Long medicId);
}