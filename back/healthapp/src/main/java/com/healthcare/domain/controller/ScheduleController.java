package com.healthcare.domain.controller;

import com.healthcare.domain.dto.request.ScheduleRequestDTO;
import com.healthcare.domain.service.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @PostMapping("/medic/{medicId}")
    public ResponseEntity<?> createSchedule(
            @PathVariable Long medicId, @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        return scheduleService.createSchedule(medicId, scheduleRequestDTO);
    }

    @GetMapping("/medic/{medicId}")
    public ResponseEntity<?> getAllSchedulesByMedicId(@PathVariable Long medicId) {
        return scheduleService.getAllSchedulesByMedicId(medicId);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<?> getScheduleById(@PathVariable Long scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<?> updateSchedule(
            @PathVariable Long scheduleId, ScheduleRequestDTO scheduleRequestDTO) {
        return scheduleService.updateSchedule(scheduleId, scheduleRequestDTO);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId) {
        return scheduleService.deleteSchedule(scheduleId);
    }
}