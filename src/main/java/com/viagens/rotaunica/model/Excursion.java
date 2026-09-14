package com.viagens.rotaunica.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "excursions")
@Getter @Setter @NoArgsConstructor
public class Excursion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(length = 2000)
	private String description;
	
	private String location;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	private Integer duration;
	
	private Integer maxParticipants;
	
	@ManyToOne
	@JoinColumn(name = "guide_id")
	private User guide;
}
