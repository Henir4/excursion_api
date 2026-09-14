package com.viagens.rotaunica.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viagens.rotaunica.model.Excursion;

public interface ExcursionRepository extends JpaRepository<Excursion, Long>{
	List<Excursion> findByLocationContainingIgnoreCase(String location);
	List<Excursion> findByPriceBetween(BigDecimal min, BigDecimal max);
	List<Excursion> findByGuideId(Long guideId);
}
