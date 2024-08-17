package com.example.reservationSystem.service;

import com.example.reservationSystem.model.Employee;
import java.util.List;
import java.util.Optional;


public interface EmployeeService {
	List<Employee> getAll();
	Optional<Employee> getById(Long id);
	void add(Employee employee);
	void delete(Employee employee);
	Optional<Employee> getByLogin(String username);
}
