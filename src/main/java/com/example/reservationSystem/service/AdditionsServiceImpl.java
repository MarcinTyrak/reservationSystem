package com.example.reservationSystem.service;

import com.example.reservationSystem.model.Addition;
import com.example.reservationSystem.repo.AdditionsRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdditionsServiceImpl implements AdditionsService {
	private final AdditionsRepo additionsRepo;

	@Autowired
	public AdditionsServiceImpl(AdditionsRepo additionsRepo) {
		this.additionsRepo = additionsRepo;
	}

	@Override
	public List<Addition> getAll() {
		return additionsRepo.findAll();
	}

	@Override
	public Optional<Addition> getById(Long id) {
		return additionsRepo.getAdditionsById(id);
	}

	@Override
	public void add(Addition addition) {
		additionsRepo.save(addition);
	}

	@Override
	public void delete(Addition addition) {
		additionsRepo.delete(addition);
	}
}
