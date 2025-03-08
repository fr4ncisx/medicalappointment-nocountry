package com.healthcare.domain.dto.response;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ScheduleResponseDTO {
    @NotNull private LocalDateTime start;
    @NotNull private LocalDateTime end;
    @NotNull private DayOfWeek dayOfWeek;
}