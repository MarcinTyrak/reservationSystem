package com.example.reservationSystem.controller;

import com.example.reservationSystem.model.Employee;
import com.example.reservationSystem.service.EmployeeService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	private final EmployeeService employeeService;

	@Autowired
	public LoginController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	@GetMapping("/")
	public String login() {
		return "login";
	}

	@Transactional
	@PostMapping("/")
	public ModelAndView login(String employeeLogin) {
		Optional<Employee> employeeOpt = employeeService.getByLogin(employeeLogin);
		if (employeeOpt.isPresent()) {
			Employee employee = employeeOpt.get();
			return new ModelAndView("redirect:/users/{employeeId}", "employeeId", employee.getId());
		}
		return new ModelAndView("redirect:/", "badLogin", "");
	}
}
