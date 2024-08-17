package com.example.reservationSystem.service;

import com.example.reservationSystem.model.Addition;
import java.util.List;
import java.util.Optional;


public interface AdditionsService {
	List<Addition> getAll();
	Optional<Addition> getById(Long id);
	void add(Addition addition);
	void delete(Addition addition);
}
