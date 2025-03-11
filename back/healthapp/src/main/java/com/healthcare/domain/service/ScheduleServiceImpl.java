package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.ScheduleRequest;
import com.healthcare.domain.dto.response.ScheduleResponse;
import com.healthcare.domain.exceptions.InvalidDataException;
import com.healthcare.domain.exceptions.MedicNotFoundException;
import com.healthcare.domain.exceptions.NotFoundInDatabaseException;
import com.healthcare.domain.model.entity.Medic;
import com.healthcare.domain.model.entity.Schedule;
import com.healthcare.domain.repository.MedicRepository;
import com.healthcare.domain.repository.ScheduleRepository;
import com.healthcare.domain.utils.Response;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements IScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MedicRepository medicRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseEntity<?> createSchedule(Long medicId, ScheduleRequest scheduleRequest) {
        Medic medic = getMedic(medicId);

        boolean existSchedule = scheduleRepository.
                existsByMedicIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                medicId,
                scheduleRequest.getStartDate(), scheduleRequest.getEndDate(),
                scheduleRequest.getStartTime(), scheduleRequest.getEndTime()
        );

        if(existSchedule) {
            throw new InvalidDataException("El médico ya tiene un horario en este rango de fechas");
        }

        Schedule schedule = new Schedule(scheduleRequest, medic);
        scheduleRepository.save(schedule);

        return ResponseEntity.status(HttpStatus.CREATED).body(Response.create("message", "Horario creado con éxito"));
    }

    @Override
    public ResponseEntity<?> getAllSchedulesByMedicId(Long medicId) {
        Medic medic = getMedic(medicId);

        List<Schedule> schedules = medic.getSchedules();

        if(schedules.isEmpty()) {
            throw new NotFoundInDatabaseException("No se encontraron horarios para este médico");
        }

        List<ScheduleResponse> scheduleResponses = schedules.stream()
                .map(schedule -> modelMapper.map(schedule, ScheduleResponse.class))
                .toList();

        return ResponseEntity.ok(scheduleResponses);
    }

    @Override
    public ResponseEntity<?> getScheduleById(Long scheduleId) {
        Schedule schedule = getSchedule(scheduleId);

        ScheduleResponse scheduleResponse = modelMapper.map(schedule, ScheduleResponse.class);

        return ResponseEntity.ok(scheduleResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateSchedule(Long scheduleId, ScheduleRequest scheduleRequest) {
        Schedule schedule = getSchedule(scheduleId);

        Schedule updatedSchedule = new Schedule(scheduleRequest, schedule.getMedic());
        updatedSchedule.setId(schedule.getId());

        scheduleRepository.save(updatedSchedule);

        ScheduleResponse scheduleResponse = modelMapper.map(updatedSchedule, ScheduleResponse.class);

        return ResponseEntity.ok(scheduleResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteSchedule(Long scheduleId) {
        Schedule schedule = getSchedule(scheduleId);
        scheduleRepository.delete(schedule);
        return ResponseEntity.ok(Response.create("message", "Horario eliminado con éxito"));
    }

    private Schedule getSchedule(Long id){
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundInDatabaseException("Horario no encontrado"));
    }

    private Medic getMedic(Long id){
        return medicRepository.findById(id)
                .orElseThrow(() -> new MedicNotFoundException("Médico no encontrado"));
    }
}