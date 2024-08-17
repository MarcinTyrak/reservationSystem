package com.example.reservationSystem.repo;

import com.example.reservationSystem.model.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	Optional<Employee> getEmployeeById(Long id);
	Optional<Employee> getByLogin(String login);
}
