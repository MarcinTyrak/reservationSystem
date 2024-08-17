package com.example.reservationSystem.repo;

import com.example.reservationSystem.model.Addition;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdditionsRepo extends JpaRepository<Addition, Long> {
	Optional<Addition> getAdditionsById(Long id);
}
