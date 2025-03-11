package com.healthcare.domain.controller;

import com.healthcare.domain.dto.request.ScheduleRequest;
import com.healthcare.domain.service.ScheduleServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @PreAuthorize("hasAnyRole({'ADMIN', 'MEDICO'})")
    @PostMapping("/medic/{medicId}")
    public ResponseEntity<?> createSchedule(
            @PathVariable Long medicId, @RequestBody @Valid ScheduleRequest scheduleRequest) {
        return scheduleService.createSchedule(medicId, scheduleRequest);
    }

    @GetMapping("/medic/{medicId}")
    public ResponseEntity<?> getAllSchedulesByMedicId(@PathVariable Long medicId) {
        return scheduleService.getAllSchedulesByMedicId(medicId);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<?> getScheduleById(@PathVariable Long scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }

    @PreAuthorize("hasAnyRole({'ADMIN', 'MEDICO'})")
    @PutMapping("/{scheduleId}")
    public ResponseEntity<?> updateSchedule(
            @PathVariable Long scheduleId, @RequestBody @Valid ScheduleRequest scheduleRequest) {
        return scheduleService.updateSchedule(scheduleId, scheduleRequest);
    }

    @PreAuthorize("hasAnyRole({'ADMIN', 'MEDICO'})")
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId) {
        return scheduleService.deleteSchedule(scheduleId);
    }
}