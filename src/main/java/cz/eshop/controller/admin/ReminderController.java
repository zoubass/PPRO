package cz.eshop.controller.admin;

import cz.eshop.model.User;
import cz.eshop.service.ReminderService;
import cz.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web")
public class ReminderController {

	@Autowired
	private UserService userService;
	@Autowired
	private ReminderService reminderService;

	@RequestMapping("/reminder")
	public String showReminders(Model model) {
		model.addAttribute("remindedUsers", userService.findUsersWithReminder());
		return "reminder";
	}

	@RequestMapping(value = "/payReminder", method = RequestMethod.GET)
	public String payReminder(Model model, @RequestParam Long userId){
		User user = userService.findById(userId);
		reminderService.payReminder(user);
		model.addAttribute("remindedUsers", userService.findUsersWithReminder());
		return "reminder";
	}
}
