package com.viagens.rotaunica.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.viagens.rotaunica.dto.ExcursionRequest;
import com.viagens.rotaunica.dto.ExcursionResponse;
import com.viagens.rotaunica.model.Excursion;
import com.viagens.rotaunica.repositories.ExcursionRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcursionService {
	
	private final ExcursionRepository excursionRepository;
	
	public List<ExcursionResponse> findAll() {
		return excursionRepository.findAll().stream()
															.map(this::toResponse).
															toList();
	}
	
	public ExcursionResponse findById(Long id) {
		Excursion e = excursionRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Excursion not found: " + id));
		return toResponse(e);
	}
	
	public List<ExcursionResponse> search (String location, BigDecimal minPrice, BigDecimal maxPrice) {
		List<Excursion> results;
		if (location != null) {
			results = excursionRepository.findByLocationContainingIgnoreCase(location);
		} else if (minPrice != null && maxPrice !=null)  {
			results = excursionRepository.findByPriceBetween(minPrice, maxPrice);
		} else {
			return results.stream().map(this::toResponse).toList();
		}
	}
	
	@Transactional
	public ExcursionResponse create(ExcursionRequest req, User guide) {
		Excursion e = new Excursion();
		e.setTitle(req.title());
    e.setDescription(req.description());
    e.setLocation(req.location());
    e.setPrice(req.price());
    e.setDurationMinutes(req.durationMinutes());
    e.setMaxParticipants(req.maxParticipants());
    e.setGuide(guide);
    return toResponse(excursionRepository.save(e));
	}
	
	private ExcursionResponse toResponse(Excursion e) {
    return new ExcursionResponse(
        e.getId(), e.getTitle(), e.getDescription(), e.getLocation(),
        e.getPrice(), e.getDuration(), e.getMaxParticipants(),
        e.getGuide() != null ? e.getGuide().getFullName() : null
    );
}

}
