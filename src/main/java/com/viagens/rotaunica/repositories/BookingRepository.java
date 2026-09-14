package com.viagens.rotaunica.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.viagens.rotaunica.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	List<Booking> findByUserId(Long userId);
	List<Booking> findByExcursionId(Long excursionId);
	@Query("SELECT COALESCE(SUM(b.participants), 0) FROM Booking b " +
      	 "WHERE b.excursion.id = :excursionId AND b.excursionDate = :date " +
      	 "AND b.status <> 'CANCELLED'")
	
	int countParticipantsForDate(@Param("excursionId") Long excursionId,
                             @Param("date") LocalDateTime date);

}
