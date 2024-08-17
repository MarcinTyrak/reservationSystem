package com.example.reservationSystem.controller;

import com.example.reservationSystem.model.Addition;
import com.example.reservationSystem.model.Employee;
import com.example.reservationSystem.model.Reservation;
import com.example.reservationSystem.model.Room;
import com.example.reservationSystem.model.dto.ReservationDTO;
import com.example.reservationSystem.service.AdditionsService;
import com.example.reservationSystem.service.EmployeeService;
import com.example.reservationSystem.service.ReservationService;
import com.example.reservationSystem.service.RoomService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ReservationController {
	private final ReservationService reservationService;
	private final EmployeeService employeeService;
	private final RoomService roomService;
	private final AdditionsService additionsService;

	@Autowired
	public ReservationController(ReservationService reservationService, EmployeeService employeeService, RoomService roomService, AdditionsService additionsService) {
		this.reservationService = reservationService;
		this.employeeService = employeeService;
		this.roomService = roomService;
		this.additionsService = additionsService;
	}


	@GetMapping(value = "/reservations", params = "loggedId")
	public String reservations(Model model, @RequestParam Long loggedId) {
		Employee logged = employeeService.getById(loggedId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("logged", logged);

		List<Reservation> reservations = reservationService.getAll();
		model.addAttribute("reservations", reservations);
		return "reservations";
	}

	@GetMapping(value = "/reservations/add", params = "loggedId")
	public ModelAndView addReservations(Model model, @RequestParam Long loggedId) {
		Employee logged = employeeService.getById(loggedId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("logged", logged);
		ReservationDTO reservationDTO = getNewReservationDTO(logged);
		List<Room> rooms = roomService.getAll();
		List<Addition> additions = additionsService.getAll();
		List<Long> reservationAdditionsIds = reservationDTO.getAdditions() != null ? reservationDTO.getAdditions().stream().map(Addition::getId).toList() : new ArrayList<>();
		return new ModelAndView("reservationAdd", Map.of("reservation", reservationDTO, "employee", logged, "allRooms", rooms, "additions", additions, "reservationAdditionsIds", reservationAdditionsIds));
	}

	@Transactional
	@PostMapping(value = "/reservations/add", params = "loggedId")
	public ModelAndView addReservations(Model model, @ModelAttribute ReservationDTO reservationDTO, RedirectAttributes redirectAttributes, @RequestParam Long loggedId) {
		Employee logged = employeeService.getById(loggedId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("logged", logged);

		try {
			Reservation reservationBase = convertReservation(reservationDTO);
			update(reservationDTO, reservationBase);

			reservationService.add(reservationBase);
			redirectAttributes.addFlashAttribute("msgOK", "Sucesfully added reservation for " + reservationDTO.getEmployee().getName());
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgErr", "Room is already taken at your time. Change time or room.");
			return new ModelAndView("redirect:/reservations/add?loggedId=" + loggedId);
		}
		return new ModelAndView("redirect:/reservations?loggedId=" + loggedId);
	}

	@Transactional
	@GetMapping(value = "/reservations/{id}/edit", params = "loggedId")
	public ModelAndView editReservations(Model model, @PathVariable Long id, @RequestParam Long loggedId) {
		Employee logged = employeeService.getById(loggedId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("logged", logged);

		Reservation reservationBase = reservationService.getById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		ReservationDTO reservationDTO = convertReservation(reservationBase);
		List<Room> allRooms = roomService.getAll();
		List<Addition> allAdditions = additionsService.getAll();
		List<Long> reservationAdditionsIds = reservationDTO.getAdditions() != null ? reservationDTO.getAdditions().stream().map(Addition::getId).toList() : new ArrayList<>();
		return new ModelAndView("reservationEdit", Map.of("reservation", reservationDTO, "employee", logged, "allRooms", allRooms, "allAdditions", allAdditions, "reservationAdditionsIds", reservationAdditionsIds));
	}

	@Transactional
	@PostMapping(value = "/reservations/{id}/edit", params = "loggedId")
	public ModelAndView editReservations(Model model, @ModelAttribute ReservationDTO reservationDTO, RedirectAttributes redirectAttributes, @RequestParam Long loggedId) {
		Employee logged = employeeService.getById(loggedId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("logged", logged);

		try {
			Reservation reservationBase = reservationService.getById(reservationDTO.getId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
			update(reservationDTO, reservationBase);

			reservationService.add(reservationBase);
			redirectAttributes.addFlashAttribute("msgOK", "Sucesfully edited reservation for " + reservationDTO.getEmployee().getName());
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgErr", "Room is already taken at your time. Change time or room.");
			return new ModelAndView("redirect:/reservations/" + reservationDTO.getId() + "/edit?loggedId=" + loggedId);
		}
		return new ModelAndView("redirect:/reservations?loggedId=" + loggedId);
	}

	@Transactional
	@GetMapping(value = "/reservations/{reservationId}/delete", params = "loggedId")
	public ModelAndView delete(Model model, @PathVariable Long reservationId, RedirectAttributes redirectAttributes, @RequestParam Long loggedId) {
		Employee logged = employeeService.getById(loggedId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("logged", logged);

		Reservation reservationBase = reservationService.getById(reservationId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		deleteRelations(reservationBase);

		reservationService.delete(reservationBase);
		redirectAttributes.addFlashAttribute("msgOK", "Sucesfully deleted reservation");
		return new ModelAndView("redirect:/reservations?loggedId=" + loggedId);
	}

	private void update(ReservationDTO reservationDTO, Reservation reservationBase) {
		deleteRelations(reservationBase);
		updateReservationExceptAdditions(reservationBase, reservationDTO);

		List<Addition> additions = new ArrayList<>();
		if (reservationDTO.getAdditions() != null) {
			for (Addition add : reservationDTO.getAdditions()) {
				Addition additionBase = additionsService.getById(add.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
				additions.add(additionBase);
				if (add.getReservations() == null) {
					add.setReservations(new ArrayList<>());
				}
				if (additionBase.getReservations() != null) {
					additionBase.getReservations().add(reservationBase);
				}
			}
		}
		reservationBase.setAdditions(additions);
	}

	private void deleteRelations(Reservation reservationBase) {
		if (reservationBase.getAdditions() != null) {
			for (Addition addition : reservationBase.getAdditions()) {
				Addition additionBase = additionsService.getById(addition.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
				if (additionBase.getReservations() != null) {
					additionBase.getReservations().remove(reservationBase);
				}
			}
		}
	}

	private void updateReservationExceptAdditions(Reservation reservationToUpdate, ReservationDTO reservationDTO) {
		LocalDateTime date = LocalDateTime.parse(reservationDTO.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"));
		Duration duraion = Duration.parse(String.format("PT%dH%dM", reservationDTO.getDurationHour(), reservationDTO.getDurationMinutes()));

		reservationToUpdate.setId(reservationDTO.getId());
		reservationToUpdate.setSubject(reservationDTO.getSubject());
		reservationToUpdate.setInformation(reservationDTO.getInformation());
		reservationToUpdate.setDate(date);
		reservationToUpdate.setDuration(duraion);
		reservationToUpdate.setEmployee(reservationDTO.getEmployee());
		reservationToUpdate.setRoom(reservationDTO.getRoom());
		reservationToUpdate.setCreate(LocalDateTime.now());
	}

	private Reservation convertReservation(ReservationDTO reservationDTO) {
		LocalDateTime date = LocalDateTime.parse(reservationDTO.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"));
		Duration duraion = Duration.parse(String.format("PT%dH%dM", reservationDTO.getDurationHour(), reservationDTO.getDurationMinutes()));

		Reservation reservation = new Reservation();
		reservation.setId(reservationDTO.getId());
		reservation.setSubject(reservationDTO.getSubject());
		reservation.setInformation(reservationDTO.getInformation());
		reservation.setDate(date);
		reservation.setDuration(duraion);
		reservation.setEmployee(reservationDTO.getEmployee());
		reservation.setRoom(reservationDTO.getRoom());
		reservation.setAdditions(reservationDTO.getAdditions());
		reservation.setCreate(LocalDateTime.now());
		return reservation;
	}

	private ReservationDTO convertReservation(Reservation reservation) {
		ReservationDTO dto = new ReservationDTO();
		dto.setId(reservation.getId());
		dto.setSubject(reservation.getSubject());
		dto.setInformation(reservation.getInformation());
		dto.setDate(reservation.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm")));
		dto.setDurationHour(reservation.getDuration().toHoursPart());
		dto.setDurationMinutes(reservation.getDuration().toMinutesPart());
		dto.setEmployee(reservation.getEmployee());
		dto.setRoom(reservation.getRoom());
		dto.setAdditions(reservation.getAdditions());
		return dto;
	}

	private static ReservationDTO getNewReservationDTO(Employee logged) {
		ReservationDTO dto = new ReservationDTO();
		dto.setEmployee(logged);
		dto.setDurationMinutes(45);
		dto.setDurationHour(0);
		return dto;
	}
}
