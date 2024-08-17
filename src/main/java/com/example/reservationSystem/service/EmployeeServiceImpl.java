package com.example.reservationSystem.service;

import com.example.reservationSystem.model.Employee;
import com.example.reservationSystem.repo.EmployeeRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepo employeeRepo;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}

	@Override
	public Optional<Employee> getById(Long id) {
		return employeeRepo.getEmployeeById(id);
	}

	@Override
	public void add(Employee employee) {
		employeeRepo.save(employee);
	}

	@Override
	public void delete(Employee employee) {
		employeeRepo.delete(employee);
	}

	@Override
	public Optional<Employee> getByLogin(String username) {
		return employeeRepo.getByLogin(username);
	}
}
