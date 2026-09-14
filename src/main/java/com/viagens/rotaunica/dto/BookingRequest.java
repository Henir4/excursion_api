package com.viagens.rotaunica.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookingRequest(
		@NotNull Long excursionId,
		@NotNull @Future LocalDateTime excursionDate,
		@NotNull @Positive Integer participants
		) {}
