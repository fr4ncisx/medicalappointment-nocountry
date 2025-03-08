package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.ScheduleRequestDTO;
import com.healthcare.domain.dto.response.ScheduleResponseDTO;
import com.healthcare.domain.exceptions.MedicNotFoundException;
import com.healthcare.domain.exceptions.NotFoundInDatabaseException;
import com.healthcare.domain.model.entity.Medic;
import com.healthcare.domain.model.entity.Schedule;
import com.healthcare.domain.repository.MedicRepository;
import com.healthcare.domain.repository.ScheduleRepository;
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
public class ScheduleServiceImpl implements IScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MedicRepository medicRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseEntity<?> createSchedule(Long medicId, ScheduleRequestDTO scheduleRequestDTO) {
        Medic medic = medicRepository.findById(medicId)
                .orElseThrow(() -> new MedicNotFoundException("Médico no encontrado"));

        Schedule schedule = new Schedule(scheduleRequestDTO, medic);
        scheduleRepository.save(schedule);

        ScheduleResponseDTO scheduleResponseDTO = modelMapper.map(schedule, ScheduleResponseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Horario creado con éxito",
                "schedule", scheduleResponseDTO
        ));
    }

    @Override
    public ResponseEntity<?> getAllSchedulesByMedicId(Long medicId) {
        Medic medic = medicRepository.findById(medicId)
                .orElseThrow(() -> new MedicNotFoundException("Médico no encontrado"));

        List<Schedule> schedules = scheduleRepository.findByMedicId(medic.getId());

        if(schedules.isEmpty()) {
            throw new NotFoundInDatabaseException("No se encontraron horarios para este médico");
        }

        List<ScheduleResponseDTO> scheduleResponseDTOS = schedules.stream()
                .map(schedule -> modelMapper.map(schedule, ScheduleResponseDTO.class))
                .toList();

        return ResponseEntity.ok(Map.of("schedule", scheduleResponseDTOS));
    }

    @Override
    public ResponseEntity<?> getScheduleById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundInDatabaseException("Horario no encontrado"));

        ScheduleResponseDTO scheduleResponseDTO = modelMapper.map(schedule, ScheduleResponseDTO.class);

        return ResponseEntity.ok(Map.of("schedule", scheduleResponseDTO));
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateSchedule(Long scheduleId, ScheduleRequestDTO scheduleRequestDTO) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundInDatabaseException("Horario no encontrado"));

        Schedule updatedSchedule = new Schedule(scheduleRequestDTO);
        updatedSchedule.setId(schedule.getId());

        scheduleRepository.save(updatedSchedule);

        ScheduleResponseDTO scheduleResponseDTO = modelMapper.map(updatedSchedule, ScheduleResponseDTO.class);

        return ResponseEntity.ok(Map.of(
                "message", "Horario actualizado con éxito",
                "schedule", scheduleResponseDTO
        ));
    }

    @Override
    public ResponseEntity<?> deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundInDatabaseException("Horario no encontrado"));

        scheduleRepository.delete(schedule);

        return ResponseEntity.ok(Map.of("message", "Horario eliminado con éxito"));
    }
}