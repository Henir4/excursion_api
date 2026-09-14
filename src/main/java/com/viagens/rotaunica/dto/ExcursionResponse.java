package com.viagens.rotaunica.dto;

import java.math.BigDecimal;

public record ExcursionResponse(
		Long id, String title, String description, String location,
		BigDecimal price, Integer duration, Integer MaxParticipants,
		String guideName
		) {}
