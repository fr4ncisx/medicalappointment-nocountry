package com.healthcare.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO {
    @NotNull private LocalDateTime start;
    @NotNull private LocalDateTime end;
    @NotNull private DayOfWeek dayOfWeek;
}