package com.example.reservationSystem.service;

import com.example.reservationSystem.model.Reservation;
import java.util.List;
import java.util.Optional;


public interface ReservationService {
	List<Reservation> getAll();
	Optional<Reservation> getById(Long id);
	void add(Reservation reservation) throws Exception;
	void delete(Reservation reservation);
}
