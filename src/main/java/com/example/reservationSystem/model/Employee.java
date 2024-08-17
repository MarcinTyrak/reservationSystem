package com.example.reservationSystem.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.List;


@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String login;

	@Nullable
	private String password;

	@NotNull
	private String name;

	@NotNull
	private String surname;

	@Nullable
	private String phone;

	@NotNull
	private String email;

	@Nullable
	@OneToMany(mappedBy = "employee")
	private List<Report> reports;

	@Nullable
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
	private List<Reservation> reservations;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Nullable
	public String getPassword() {
		return password;
	}

	public void setPassword(@Nullable String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Nullable
	public String getPhone() {
		return phone;
	}

	public void setPhone(@Nullable String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Nullable
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(@Nullable List<Report> reports) {
		this.reports = reports;
	}

	@Nullable
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(@Nullable List<Reservation> reservations) {
		this.reservations = reservations;
	}
}
