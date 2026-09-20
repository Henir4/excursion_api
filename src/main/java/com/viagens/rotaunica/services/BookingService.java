package com.viagens.rotaunica.services;

import org.springframework.stereotype.Service;

import com.viagens.rotaunica.dto.BookingRequest;
import com.viagens.rotaunica.model.Booking;
import com.viagens.rotaunica.model.BookingStatus;
import com.viagens.rotaunica.model.Excursion;
import com.viagens.rotaunica.model.User;
import com.viagens.rotaunica.repositories.BookingRepository;
import com.viagens.rotaunica.repositories.ExcursionRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepository bookingRepository;
	private final ExcursionRepository excursionRepository;
	
	@Transactional
	public Booking createBooking(BookingRequest req, User user) {
		Excursion excursion = excursionRepository.findById(req.excursionId())
				.orElseThrow(() -> new EntityNotFoundException("Excursion not found"));
		
		int alreadyBooked = bookingRepository.countParticipantsForDate(
				excursion.getId(), req.excursionDate());
		
		if (alreadyBooked + req.participants() > excursion.getMaxParticipants()) {
			throw new IllegalStateException("Not enough spots avaible for this date");
		}
		
		Booking booking = new Booking();
		booking.setUser(user);
		booking.setExcursion(excursion);
		booking.setExcursionDate(req.excursionDate());
		booking.setParticipants(req.participants());
		booking.setStatus(BookingStatus.PENDING);
		
		return bookingRepository.save(booking);
	}
	
}
