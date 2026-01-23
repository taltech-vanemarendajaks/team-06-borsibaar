package com.borsibaar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record BarStationRequestDto(
        @NotBlank(message = "Station name is required")String name,
        @Size(max = 1000, message = "Description must not exceed 1000 characters")String description,
        @NotNull Boolean isActive,
    List<UUID> userIds // TODO: Decide if userIds must be non-empty
) {
}