package com.healthcare.infrastructure.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestLoginDTO(@NotNull @NotBlank String username, @NotNull @NotBlank String password) {}
