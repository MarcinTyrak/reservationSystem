package com.example.reservationSystem.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String subject;

	@NotNull
	@Lob
	private String information;

	@NotNull
	private LocalDateTime date;

	@NotNull
	private Duration duration;

	@NotNull
	@ManyToOne
	private Employee employee;

	@NotNull
	@ManyToOne
	private Room room;

	@Nullable
	@ManyToMany(mappedBy = "reservations", fetch = FetchType.LAZY)
	private List<Addition> additions;

	@NotNull
	private LocalDateTime create;


	public String displayDuration() {
		long HH = duration.toHours();
		long MM = duration.toMinutesPart();
		String format = HH < 10 ? "%01d h. %02d min." : "%02d h. %02d min.";
		return String.format(format, HH, MM);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Nullable
	public List<Addition> getAdditions() {
		return additions;
	}

	public void setAdditions(@Nullable List<Addition> additions) {
		this.additions = additions;
	}

	public LocalDateTime getCreate() {
		return create;
	}

	public void setCreate(LocalDateTime create) {
		this.create = create;
	}
}
