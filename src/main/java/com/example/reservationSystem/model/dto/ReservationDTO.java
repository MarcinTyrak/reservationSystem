package com.example.reservationSystem.model.dto;

import com.example.reservationSystem.model.Addition;
import com.example.reservationSystem.model.Employee;
import com.example.reservationSystem.model.Room;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;


public class ReservationDTO {

	@NotNull
	private Long id;

	@NotNull
	private String subject;

	@NotNull
	private String information;

	@NotNull
	private String date;

	@NotNull
	@Min(0)
	@Max(24)
	private Integer durationHour;

	@NotNull
	@Min(1)
	@Max(60)
	private Integer durationMinutes;

	@NotNull
	private Employee employee;

	@NotNull
	private Room room;

	@Nullable
	private List<Addition> additions;


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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getDurationHour() {
		return durationHour;
	}

	public void setDurationHour(Integer durationHour) {
		this.durationHour = durationHour;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
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
}
