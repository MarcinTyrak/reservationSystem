package com.example.reservationSystem.controller;

import com.example.reservationSystem.model.Employee;
import com.example.reservationSystem.service.EmployeeService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class EmployeeController {
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	@GetMapping("/users/{id}")
	public String home(Model model,  @PathVariable Long id) {
		Optional<Employee> employeeOpt = employeeService.getById(id);
		if (employeeOpt.isEmpty()) {
			return "404";
		}
		Employee logged = employeeOpt.get();
		model.addAttribute("logged", logged);
		model.addAttribute("reservations", logged.getReservations());
		return "home";
	}
}
