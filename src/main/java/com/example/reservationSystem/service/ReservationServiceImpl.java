package com.example.reservationSystem.service;

import com.example.reservationSystem.model.Reservation;
import com.example.reservationSystem.repo.ReservationRepo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationRepo reservationRepo;

	@Autowired
	public ReservationServiceImpl(ReservationRepo reservationRepo) {
		this.reservationRepo = reservationRepo;
	}

	@Override
	public List<Reservation> getAll() {
		return reservationRepo.findAll();
	}

	@Override
	public Optional<Reservation> getById(Long id) {
		return reservationRepo.getReservationById(id);
	}

	@Override
	public void add(Reservation reservation) throws Exception {
		LocalDateTime date = reservation.getDate();
		LocalDateTime begin = date.toLocalDate().atStartOfDay();
		LocalDateTime end = date.toLocalDate().plusDays(1).atStartOfDay();
		List<Reservation> reservations = reservationRepo.getReservationBeetwenDates(reservation.getRoom(), begin, end);
		if (checkIfAvailable(reservations, reservation)) {
			reservationRepo.save(reservation);
		} else {
			throw new Exception();
		}
	}

	private boolean checkIfAvailable(List<Reservation> allReservations, Reservation reservation) {
		List<Reservation> allReservationsCopy = new ArrayList<>(allReservations);
		allReservationsCopy.removeIf(existingReservation -> existingReservation.getId().equals(reservation.getId()) && existingReservation.getRoom().getId().equals(reservation.getRoom().getId()));

		LocalDateTime newReservationStart = reservation.getDate();
		LocalDateTime newReservationEnd = newReservationStart.plus(reservation.getDuration());

		for (Reservation existingReservation : allReservationsCopy) {
			LocalDateTime existingReservationStart = existingReservation.getDate();
			LocalDateTime existingReservationEnd = existingReservationStart.plus(existingReservation.getDuration());

			// Sprawdzanie, czy zakresy czasowe się przecinają
			if (newReservationStart.isBefore(existingReservationEnd) && newReservationEnd.isAfter(existingReservationStart)) {
				return false; // Kolizja, termin jest zajęty
			}
		}

		return true; // Brak kolizji, termin jest wolny
	}

	@Override
	public void delete(Reservation reservation) {
		reservationRepo.delete(reservation);
	}
}
