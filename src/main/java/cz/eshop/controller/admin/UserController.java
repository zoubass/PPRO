package cz.eshop.controller.admin;

import cz.eshop.dto.UserDto;
import cz.eshop.model.User;
import cz.eshop.service.AuthoritiesService;
import cz.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(Model model, @ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, @RequestParam(value = "isEditOp", required = false) boolean isEditOp) {

		if (bindingResult.hasErrors()) {
			return prepareModel(model, userService.findAll(), userDto, isEditOp);
		}
		
		if (!isEditOp) {
			if (userService.isNotUniqueUsername(userDto)) {
				model.addAttribute("users", userService.findAll());
				model.addAttribute("userDto", userDto);
				model.addAttribute("isUsernameUsed", true);
				model.addAttribute("isEditOp", false);
				return "user";
			}	
		}

		userService.saveUser(userDto);
		return prepareModel(model, userService.findAll(), new UserDto(), false);
	}
	

	@RequestMapping(value = "/removeUser", method = RequestMethod.GET)
	public String remove(Model model, @RequestParam Long id) {
		userService.removeUser(id);
		model.addAttribute("userDto", new UserDto());
		model.addAttribute("isEditOp", false);
		model.addAttribute("users", userService.findAll());
		return "user";
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam Long id) {
		UserDto userDto = new UserDto();

		User user = userService.findById(id);
		userDto.setAuthorities(authoritiesService.findUserAuthority(user.getUsername()));
		userDto.setUser(user);
		
		model.addAttribute("userDto", userDto);
		model.addAttribute("isEditOp", true);
		model.addAttribute("users", userService.findAll());
		return "user";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUser(Model model, @ModelAttribute UserDto dto) {
		model.addAttribute("users", userService.findAll());
		model.addAttribute("userDto", new UserDto());
		model.addAttribute("isEditOp", false);

		return "user";
	}

	/**
	 * Helper methods for reuse
	 */
	private String prepareModel(Model model, Iterable<User> users, UserDto userDto, boolean isEditOp) {
		model.addAttribute("users", userService.findAll());
		model.addAttribute("userDto", userDto);
		model.addAttribute("isEditOp", isEditOp);
		return "user";
	}
}
