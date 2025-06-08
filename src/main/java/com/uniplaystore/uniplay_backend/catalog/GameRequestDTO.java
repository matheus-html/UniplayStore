package com.uniplaystore.uniplay_backend.catalog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record GameRequestDTO(
        @NotBlank
        String title,
        @NotBlank
        String genre,
        @NotNull @PositiveOrZero
        Double price,
        @NotNull @PositiveOrZero
        Integer stock,
        @NotBlank
        String description
) {}
