package com.healthcare.domain.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.healthcare.domain.model.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Status status;

}
