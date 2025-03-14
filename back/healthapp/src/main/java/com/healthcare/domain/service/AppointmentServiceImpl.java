package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.AppointmentRequest;
import com.healthcare.domain.dto.response.AppointmentListResponse;
import com.healthcare.domain.dto.response.AppointmentResponse;
import com.healthcare.domain.exceptions.*;
import com.healthcare.domain.model.entity.Appointment;
import com.healthcare.domain.model.entity.Medic;
import com.healthcare.domain.model.entity.Patient;
import com.healthcare.domain.model.entity.Schedule;
import com.healthcare.domain.model.enums.Status;
import com.healthcare.domain.repository.AppointmentRepository;
import com.healthcare.domain.repository.MedicRepository;
import com.healthcare.domain.repository.PatientRepository;
import com.healthcare.domain.service.interfaces.IAppointmentService;

import jakarta.mail.MessagingException;
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
public class AppointmentServiceImpl implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final MedicRepository medicRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final MailService mailService;

    private static final String MESSAGE = "message";
    private static final String APPOINTMENT = "appointment";
    private static final String APPOINTMENTS = "appointments";

    @Override
    @Transactional
    public ResponseEntity<?> scheduleAppointment(Long patientId, Long medicId, AppointmentRequest appointmentRequest) throws MessagingException {
        var medic = getMedicFromRepository(medicId);
        var patient = getPatientFromRepository(patientId);
        var medicSchedule = medic.getSchedules();
        isTimeTaken(medic, appointmentRequest);
        outOfTimeRangeValidation(medicSchedule, appointmentRequest);
        Appointment appointment = new Appointment(appointmentRequest, medic, patient);
        appointmentRepository.save(appointment);
        mailService.sendMail(patient.getUser().getEmail(), "Cita Médica: Confirmación", appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                MESSAGE, "Cita agendada correctamente",
                APPOINTMENT, modelMapper.map(appointment, AppointmentResponse.class)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateAppointment(Long appointmentId, AppointmentRequest appointmentRequest) {
        var appointment = getAppointment(appointmentId);
        checkIfNotConfirmed(appointment);
        var mediSchedule = appointment.getMedic().getSchedules();
        isTimeTaken(appointment.getMedic(), appointmentRequest);
        outOfTimeRangeValidation(mediSchedule, appointmentRequest);
        modelMapper.map(appointmentRequest,appointment);
        appointmentRepository.save(appointment);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                MESSAGE, "Cita reagendada correctamente",
                APPOINTMENT, modelMapper.map(appointment, AppointmentResponse.class)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> cancelAppointment(Long appointmentId) throws MessagingException {
        var appointment = getAppointment(appointmentId);
        checkIfNotConfirmed(appointment);
        appointment.setStatus(Status.CANCELADA);
        appointmentRepository.save(appointment);
        var patient = appointment.getPatient();
        mailService.sendMail(patient.getUser().getEmail(), "Cita médica: Su cita fue cancelada", appointment);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                MESSAGE, "Cita cancelada correctamente",
                APPOINTMENT, modelMapper.map(appointment, AppointmentResponse.class)));
    }

    @Override
    public ResponseEntity<?> getAppointmentsByPatient(Long patientId) {
        getPatientFromRepository(patientId);
        var response = validateListAndGetResponse(getListOfAppointments(patientId));
        return ResponseEntity.ok(Map.of(
                MESSAGE, "Lista de citas médicas",
                APPOINTMENTS, response));
    }

    private List<Appointment> getListOfAppointments(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    private List<AppointmentListResponse> validateListAndGetResponse(List<Appointment> appointments){
        if (appointments.isEmpty()) {
            throw new AppointmentNotFoundException("El paciente no tiene citas médicas registradas");
        }
        return appointments.stream()
                .map(a -> modelMapper.map(a, AppointmentListResponse.class)).toList();
    }

    private Medic getMedicFromRepository(Long id) {
        return medicRepository.findById(id).orElseThrow(() -> new MedicNotFoundException("Médico no encontrado"));
    }

    private Patient getPatientFromRepository(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Paciente no encontrado"));
    }

    public Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException("Cita no encontrada"));
    }

    public void checkIfNotConfirmed(Appointment appointment) {
        if (appointment.getStatus().equals(Status.CANCELADA) || appointment.getStatus().equals(Status.COMPLETADA)) {
            throw new CancelledAppointmentException("No se puede modificar una cita " + appointment.getStatus().toString().toLowerCase());
        }
    }

    private void isTimeTaken(Medic medic, AppointmentRequest appointmentRequest) {
        var medicAppointments = medic.getAppointment();
        medicAppointments.stream()
                .filter(t -> !t.getTime().equals(appointmentRequest.getTime()) && !t.getDate().equals(appointmentRequest.getDate()))
                .findAny()
                .orElseThrow(() -> new InvalidDataException("Ese horario ya esta asignado"));
    }

    private void outOfTimeRangeValidation(List<Schedule> medicSchedule, AppointmentRequest appointmentRequest) {
        medicSchedule.stream()
                .filter(sch -> !appointmentRequest.getTime().isAfter(sch.getEndTime()))
                .filter(sch -> !appointmentRequest.getTime().isBefore(sch.getStartTime()))
                .findAny()
                .orElseThrow(() -> new InvalidDataException("No se puede guardar una cita en ese horario"));
    }
}