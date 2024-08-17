package com.example.reservationSystem.repo;

import com.example.reservationSystem.model.Room;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
	Optional<Room> getRoomById(Long id);
}
