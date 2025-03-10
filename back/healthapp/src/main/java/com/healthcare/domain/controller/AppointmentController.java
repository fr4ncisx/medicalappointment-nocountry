package com.healthcare.domain.controller;

import com.healthcare.domain.dto.AppointmentDTO;
import com.healthcare.domain.service.IAppointmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @PreAuthorize("hasAnyRole({'ADMIN','MEDICO', 'PACIENTE'})")
    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.scheduleAppointment(
                appointmentDTO.patientId(),
                appointmentDTO.medicId(),
                appointmentDTO.dateTime()
        );
    }

    @PreAuthorize("hasAnyRole({'ADMIN','MEDICO', 'PACIENTE'})")
    @PutMapping("/update/{appointmentId}")
    public ResponseEntity<?> updateAppointment(
            @PathVariable Long appointmentId,
            @RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.updateAppointment(
                appointmentId,
                appointmentDTO.dateTime()
        );
    }

    @PreAuthorize("hasAnyRole({'ADMIN','MEDICO', 'PACIENTE'})")
    @DeleteMapping("/cancel/{appointmentId}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }

    @PreAuthorize("hasAnyRole({'ADMIN','MEDICO', 'PACIENTE'})")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }
}