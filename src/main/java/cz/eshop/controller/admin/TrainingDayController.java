package cz.eshop.controller.admin;

import cz.eshop.dto.UserDto;
import cz.eshop.model.Ticket;
import cz.eshop.model.Training;
import cz.eshop.model.User;
import cz.eshop.service.AttendanceService;
import cz.eshop.service.ReminderService;
import cz.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web")
public class TrainingDayController {

	@Autowired
	private UserService userService;
	@Autowired
	private ReminderService remService;
	@Autowired
	private AttendanceService attService;

	private Map<Long, User> usersInDebtCash = new HashMap<>();

	@RequestMapping("/trainingDay")
	public String trainingDayPreview(Model model) {
		model.addAttribute("users", userService.findAll());
		model.addAttribute("showUsersInDebt", false);
		model.addAttribute("userDto", new UserDto());
		model.addAttribute("style", "none");
		return "trainingDay";
	}

	@RequestMapping("/registerUsersAtt")
	public String registerUsersAttendance(@RequestParam(value = "present", required = false) List<String> userIds,
			Model model) {

		if (userIds != null) {

			List<Long> userIdsLong = userIds.stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
			Training actualTraining = attService.findActualTraining();
			List<User> userList = remService.doReminder(userIdsLong, actualTraining);
			attService.writeDownAttByTime(userIdsLong, actualTraining);

			usersInDebtCash = userList.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

			model.addAttribute("users", usersInDebtCash.values());
			model.addAttribute("showUsersInDebt", true);
			model.addAttribute("userDto", new UserDto());
			model.addAttribute("ticket", new Ticket());
			model.addAttribute("style", "none");
		} else {
			model.addAttribute("users", userService.findAll());
			model.addAttribute("userDto", new UserDto());
			model.addAttribute("nobodyIsChecked", true);
			model.addAttribute("style", "none");
		}

		return "trainingDay";
	}

	@RequestMapping("/registerUser")
	public String registerNewUser(Model model, @ModelAttribute @Valid UserDto userDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("users", userService.findAll());
			model.addAttribute("userDto", userDto);
			model.addAttribute("style", "block");
			return "trainingDay";
		}

		if (userService.isNotUniqueUsername(userDto)) {
			model.addAttribute("users", userService.findAll());
			model.addAttribute("userDto", userDto);
			model.addAttribute("style", "block");
			model.addAttribute("isUsernameUsed", true);
			return "trainingDay";
		}

		userService.saveNewlyRegisteredUser(userDto);
		model.addAttribute("style", "none");
		model.addAttribute("users", userService.findAll());
		model.addAttribute("userDto", new UserDto());

		return "trainingDay";
	}

	@RequestMapping("/fillTicket")
	public String fillTicketForUser(Model model, @ModelAttribute Ticket ticket, @RequestParam("userId") Long id,
			@RequestParam(value = "timeTicketValue", required = false) Integer timeTicketValue,
			@RequestParam(value = "entryTicketValue", required = false) Integer entryTicketValue) {

		Ticket assignedTicket = userService.assignTicketToUser(id, ticket, timeTicketValue, entryTicketValue);

		usersInDebtCash.remove(id);

		if (usersInDebtCash.isEmpty()) {
			return "redirect:/web/attendance";
		}

		model.addAttribute("style", "none");
		model.addAttribute("userDto", new UserDto());
		//TODO: prepared to show the assignedTicket
		//		model.addAttribute("assignedTicket", assignedTicket);
		model.addAttribute("users", usersInDebtCash.values());
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("showUsersInDebt", true);
		return "trainingDay";
	}
}
