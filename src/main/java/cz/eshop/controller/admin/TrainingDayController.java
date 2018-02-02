package cz.eshop.controller.admin;

import cz.eshop.dto.UserDto;
import cz.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TrainingDayController {

	@Autowired
	private UserService userService;

	@RequestMapping("/trainingDay")
	public String trainingDayPreview(Model model) {
		model.addAttribute("users", userService.findAll());
		model.addAttribute("showUsersInDebt", false);
		model.addAttribute("userDto", new UserDto());
		return "trainingDay";
	}
	
	@RequestMapping("/registerUsersAtt")
	public String registerUsersAttendance(@RequestParam(value = "present", required = false) List<String> userIds, Model model) {
		
		if (userIds != null) {
			
			/*
				TODO: Tady budeš volat metodu na kontrolu placení
			 */
			
			//TODO: Tady prosím nahradit userService.findAll() za list dlužníků vrácený z metody 
			model.addAttribute("users", userService.findAll());
			model.addAttribute("showUsersInDebt", true);
			model.addAttribute("userDto", new UserDto());	
		} else {
			model.addAttribute("users", userService.findAll());
			model.addAttribute("userDto", new UserDto());
			model.addAttribute("nobodyIsChecked", true);
		}
		
		return "trainingDay";
	}

	@RequestMapping("/registerUser")
	public String registerNewUser(Model model, @ModelAttribute @Valid UserDto userDto,  BindingResult bindingResult){

		if (bindingResult.hasErrors()) {
			model.addAttribute("users", userService.findAll());
			//			model.addAttribute("showUsersInDebt", false);
			model.addAttribute("userDto", userDto);
			return "trainingDay";
		}

		if (userService.isNotUniqueUsername(userDto)) {
			model.addAttribute("users", userService.findAll());
			model.addAttribute("userDto", userDto);
			model.addAttribute("isUsernameUsed", true);
			return "trainingDay";
		}

		userService.saveUser(userDto);
		model.addAttribute("users", userService.findAll());
		model.addAttribute("userDto", new UserDto());
		
		return "trainingDay";
	}
}
