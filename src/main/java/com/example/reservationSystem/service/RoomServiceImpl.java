package com.example.reservationSystem.service;

import com.example.reservationSystem.model.Room;
import com.example.reservationSystem.repo.RoomRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomServiceImpl implements RoomService {
	private final RoomRepo roomRepo;

	@Autowired
	public RoomServiceImpl(RoomRepo roomRepo) {
		this.roomRepo = roomRepo;
	}

	@Override
	public List<Room> getAll() {
		return roomRepo.findAll();
	}

	@Override
	public Optional<Room> getById(Long id) {
		return roomRepo.getRoomById(id);
	}

	@Override
	public void add(Room room) {
		roomRepo.save(room);
	}

	@Override
	public void delete(Room room) {
		roomRepo.delete(room);
	}
}
