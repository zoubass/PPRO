package cz.eshop.controller.admin;

import cz.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReminderController {

	@Autowired
	private UserService userService;

	@RequestMapping("/reminder")
	public String showReminders(Model model) {
		model.addAttribute("remindedUsers", userService.findUsersWithReminder());
		return "reminder";
	}
}
