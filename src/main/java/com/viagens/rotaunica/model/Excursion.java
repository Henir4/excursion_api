package com.viagens.rotaunica.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "excursion", cascade = CascadeType.ALL)
	private List<Booking> bookings = new ArrayList<>();
}
