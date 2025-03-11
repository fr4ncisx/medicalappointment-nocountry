package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.ScheduleRequest;
import org.springframework.http.ResponseEntity;

public interface IScheduleService {
    ResponseEntity<?> createSchedule(Long medicId, ScheduleRequest scheduleRequest);
    ResponseEntity<?> getAllSchedulesByMedicId(Long medicId);
    ResponseEntity<?> getScheduleById(Long scheduleId);
    ResponseEntity<?> updateSchedule(Long scheduleId, ScheduleRequest scheduleRequest);
    ResponseEntity<?> deleteSchedule(Long scheduleId);
}