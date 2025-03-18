package com.healthcare.domain.repository;

import com.healthcare.domain.model.entity.Appointment;
import com.healthcare.domain.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    boolean existsByMedicId(Long medicId);
    List<Appointment> findByMedicIdAndStatusOrderByDateAscTimeAsc(Long medicId, Status status);
}