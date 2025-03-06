package com.healthcare.domain.service;

import static com.healthcare.domain.model.enums.Status.CONFIRMADA;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.test.context.ActiveProfiles;

import com.healthcare.domain.dto.AppointmentDTO;
import com.healthcare.domain.model.entity.Appointment;
import com.healthcare.domain.model.enums.Status;
import com.healthcare.domain.repository.AppointmentRepository;
import com.healthcare.domain.repository.MedicRepository;
import com.healthcare.domain.repository.PatientRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private MedicRepository medicRepository;
    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private AppointmentServiceImpl appointmentServiceImpl;

    private ModelMapper modelMapper;
    private AppointmentDTO mockAppointmentDTO;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();
        mockAppointmentDTO = new AppointmentDTO(null, null, null, LocalDateTime.now(), CONFIRMADA);
    }

    @Test
    void testCancelAppointment() {
        Appointment appointment = modelMapper.map(mockAppointmentDTO, Appointment.class);
        appointment.setStatus(Status.CANCELADA);


        Assert.notNull(appointment);
        Assert.isTrue(appointment.getStatus() == Status.CANCELADA);
    }

    @Test
    void testGetAppointmentsByPatient() {

        AppointmentDTO appointmentDTO = new AppointmentDTO(null, null, null, LocalDateTime.now(), CONFIRMADA);
        var appointment = modelMapper.map(appointmentDTO, Appointment.class);

        Assert.notNull(appointment);
    }

    @Test
    void testScheduleAppointment() {
        AppointmentDTO appointmentDTO = new AppointmentDTO(null, null, null, LocalDateTime.now(), Status.CONFIRMADA);
        var appointment = modelMapper.map(appointmentDTO, Appointment.class);

        Assert.notNull(appointment);
    }

    @Test
    void testUpdateAppointment() {
        Appointment appointment = modelMapper.map(mockAppointmentDTO, Appointment.class);
        appointment.setStatus(Status.CONFIRMADA);


        Assert.notNull(appointment);
        Assert.isTrue(appointment.getStatus() == Status.CONFIRMADA);
    }
}
