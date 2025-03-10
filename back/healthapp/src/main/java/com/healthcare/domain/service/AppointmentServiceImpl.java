package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.AppointmentRequest;
import com.healthcare.domain.dto.response.AppointmentResponse;
import com.healthcare.domain.exceptions.*;
import com.healthcare.domain.model.entity.Appointment;
import com.healthcare.domain.model.entity.Medic;
import com.healthcare.domain.model.entity.Patient;
import com.healthcare.domain.model.enums.Status;
import com.healthcare.domain.repository.AppointmentRepository;
import com.healthcare.domain.repository.MedicRepository;
import com.healthcare.domain.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.healthcare.domain.model.enums.Status.CONFIRMADA;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final MedicRepository medicRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseEntity<?> scheduleAppointment(Long patientId, Long medicId, AppointmentRequest appointmentRequest) {
        Optional<Medic> medicOpt = medicRepository.findById(medicId);
        Optional<Patient> patientOpt = patientRepository.findById(patientId);

        if (medicOpt.isEmpty()) {
            throw new MedicNotFoundException("Médico no encontrado");
        }

        if (patientOpt.isEmpty()) {
            throw new PatientNotFoundException("Paciente no encontrado");
        }

        /*
         * boolean isAvailable =
         * appointmentRepository.findByMedicIdAndDateTime(medicId,)
         * .filter(appointment ->
         * !appointment.getStatus().equals(Status.CANCELADA)).isEmpty();
         * 
         * if (!isAvailable) {
         * throw new
         * MedicScheduleConflictException("El médico ya tiene una cita en este horario"
         * );
         * }
         */

        Appointment appointment = Appointment.builder()
                .medic(medicOpt.get())
                .patient(patientOpt.get())
                .date(appointmentRequest.getDate())
                .time(appointmentRequest.getTime())
                .status(CONFIRMADA)
                .build();

        appointmentRepository.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Cita agendada correctamente",
                "appointment", modelMapper.map(appointment, AppointmentResponse.class)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateAppointment(Long appointmentId, AppointmentRequest appointmentRequest) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);

        if (appointmentOpt.isEmpty()) {
            throw new AppointmentNotFoundException("Cita no encontrada");
        }

        Appointment appointment = appointmentOpt.get();

        /*if (appointment.getDateTime().equals(newDateTime)) {
            throw new AppointmentDateConflictException("La nueva fecha debe ser diferente a la actual");
        }*/

        if (appointment.getStatus().equals(Status.CANCELADA)) {
            throw new CancelledAppointmentException("No se puede modificar una cita cancelada");
        }

        /*boolean isAvailable = appointmentRepository
                .findByMedicIdAndDateTime(appointment.getMedic().getId(), newDateTime).isEmpty();

        if (!isAvailable) {
            throw new MedicScheduleConflictException("El médico ya tiene una cita en este horario");
        }*/

        //appointment.setDateTime(newDateTime);
        appointmentRepository.save(appointment);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Cita reagendada correctamente",
                "appointment", modelMapper.map(appointment, AppointmentResponse.class)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> cancelAppointment(Long appointmentId) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);

        if (appointmentOpt.isEmpty()) {
            throw new AppointmentNotFoundException("Cita no encontrada");
        }

        Appointment appointment = appointmentOpt.get();

        if (appointment.getStatus().equals(Status.CANCELADA)) {
            throw new CancelledAppointmentException("La cita ya esta cancelada");
        }

        appointment.setStatus(Status.CANCELADA);
        appointmentRepository.save(appointment);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Cita cancelada correctamente",
                "appointment", modelMapper.map(appointment, AppointmentResponse.class)));
    }

    @Override
    public ResponseEntity<?> getAppointmentsByPatient(Long patientId) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);

        if (patientOpt.isEmpty()) {
            throw new PatientNotFoundException("Paciente no encontrado");
        }

        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);

        if (appointments.isEmpty()) {
            throw new AppointmentNotFoundException("El paciente no tiene citas médicas registradas");
        }

        List<AppointmentResponse> response = appointments.stream()
                .map(a -> modelMapper.map(a, AppointmentResponse.class)).toList();

        return ResponseEntity.ok(Map.of(
                "message", "Lista de citas médicas",
                "appointments", response));
    }
}