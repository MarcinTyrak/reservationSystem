package com.example.reservationSystem.service;

import com.example.reservationSystem.model.Report;
import java.util.List;
import java.util.Optional;


public interface ReportService {
	List<Report> getAll();
	Optional<Report> getById(Long id);
	void add(Report report);
	void delete(Report report);
}
