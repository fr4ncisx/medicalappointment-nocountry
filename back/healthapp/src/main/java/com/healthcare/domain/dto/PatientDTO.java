package com.healthcare.domain.dto;

import java.time.LocalDate;

import com.healthcare.domain.model.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private String firstName;
    private String lastName;
    private String document;
    private LocalDate birthDate;
    private Gender gender;
    private String phone;
    private String address;
    private String emergencyContactInfo;
}
