package com.healthcare.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.domain.model.enums.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {
    @NotNull @NotBlank private String firstName;
    @NotNull @NotBlank private String lastName;
    @NotNull @NotBlank private String documentId;
    @NotNull private LocalDate birthDate;
    @NotNull private Gender gender;
    @NotNull @NotBlank private String phone;
    @NotNull @NotBlank private String address;
    @NotNull @NotBlank private String emergencyContactInfo;
    @JsonProperty("user")
    @NotNull private UserDTO user;
}