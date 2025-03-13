package com.healthcare.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicAppointmentsResponse {
    private Long id;
    private String name;
    private String lastname;
}