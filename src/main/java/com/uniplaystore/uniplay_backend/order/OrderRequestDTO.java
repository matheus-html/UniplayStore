package com.uniplaystore.uniplay_backend.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record OrderRequestDTO(
        @NotNull
        Long userId,
        @NotNull @PositiveOrZero
        Double totalAmount,
        @NotBlank
        String status
) {}
