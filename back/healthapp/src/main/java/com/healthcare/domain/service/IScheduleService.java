package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.ScheduleRequestDTO;
import org.springframework.http.ResponseEntity;

public interface IScheduleService {
    ResponseEntity<?> createSchedule(Long medicId, ScheduleRequestDTO scheduleRequestDTO);
    ResponseEntity<?> getAllSchedulesByMedicId(Long medicId);
    ResponseEntity<?> getScheduleById(Long scheduleId);
    ResponseEntity<?> updateSchedule(Long scheduleId, ScheduleRequestDTO scheduleRequestDTO);
    ResponseEntity<?> deleteSchedule(Long scheduleId);
}