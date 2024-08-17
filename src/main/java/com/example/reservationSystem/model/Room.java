package com.example.reservationSystem.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.List;


@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private Integer places;

	@Nullable
	@OneToMany(mappedBy = "room")
	private List<Reservation> reservations;

	@Nullable
	@OneToMany(mappedBy = "room")
	private List<Report> reports;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	@Nullable
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(@Nullable List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Nullable
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(@Nullable List<Report> reports) {
		this.reports = reports;
	}
}
