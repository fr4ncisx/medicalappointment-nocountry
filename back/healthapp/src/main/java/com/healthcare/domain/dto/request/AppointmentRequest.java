package com.healthcare.domain.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.healthcare.domain.model.enums.Status;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
    @NotNull
    private Status status;

}
