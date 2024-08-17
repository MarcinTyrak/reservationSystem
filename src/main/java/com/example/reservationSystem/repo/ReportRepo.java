package com.example.reservationSystem.repo;

import com.example.reservationSystem.model.Report;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
	Optional<Report> getReportById(Long id);
}
