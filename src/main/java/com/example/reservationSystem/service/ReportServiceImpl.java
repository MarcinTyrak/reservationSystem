package com.example.reservationSystem.service;

import com.example.reservationSystem.model.Report;
import com.example.reservationSystem.repo.ReportRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportServiceImpl implements ReportService {
	private final ReportRepo reportRepo;

	@Autowired
	public ReportServiceImpl(ReportRepo reportRepo) {
		this.reportRepo = reportRepo;
	}

	@Override
	public List<Report> getAll() {
		return reportRepo.findAll();
	}

	@Override
	public Optional<Report> getById(Long id) {
		return reportRepo.getReportById(id);
	}

	@Override
	public void add(Report report) {
		reportRepo.save(report);
	}

	@Override
	public void delete(Report report) {
		reportRepo.delete(report);
	}
}
