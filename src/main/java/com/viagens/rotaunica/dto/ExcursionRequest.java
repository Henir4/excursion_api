package com.viagens.rotaunica.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ExcursionRequest(
		@NotBlank String title,
		@Size(max = 2000) String description,
		@NotBlank String location,
		@NotNull @Positive BigDecimal price,
		@Positive Integer duration,
		@Positive Integer maxParticipants
		) {}
