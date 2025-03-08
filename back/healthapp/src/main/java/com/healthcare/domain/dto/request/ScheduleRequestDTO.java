package com.healthcare.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ScheduleRequestDTO {
    @NotNull private LocalDateTime start;
    @NotNull private LocalDateTime end;
    @NotNull private DayOfWeek dayOfWeek;
}
