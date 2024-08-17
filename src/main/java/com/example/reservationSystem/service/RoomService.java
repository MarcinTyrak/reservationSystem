package com.example.reservationSystem.service;

import com.example.reservationSystem.model.Room;
import java.util.List;
import java.util.Optional;


public interface RoomService {
	List<Room> getAll();
	Optional<Room> getById(Long id);
	void add(Room room);
	void delete(Room room);
}
