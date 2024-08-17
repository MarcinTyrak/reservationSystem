package com.example.reservationSystem.repo;

import com.example.reservationSystem.model.Reservation;
import com.example.reservationSystem.model.Room;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
	Optional<Reservation> getReservationById(Long id);

	@Query(
			"""
			select reservation
			from Reservation reservation
			where reservation.date >= :begin 
			and reservation.date <= :end 
			and reservation.room = :room
			order by reservation.date
			"""
	)
	List<Reservation> getReservationBeetwenDates(@Param("room") Room room, @Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);
}
