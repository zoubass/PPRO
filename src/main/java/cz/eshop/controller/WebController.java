package cz.eshop.controller;

import cz.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class WebController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/web/index", "/web" })
	public String showHomePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String username = auth.getName();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();

		String authority = authorities.get(0).getAuthority();
		if (authority.equals("ROLE_ADMIN")) {
			return "redirect: /user";
		} else if (authority.equals("ROLE_TRAINER")) {
			return "redirect: /training";
		} else {
			model.addAttribute("ticket", userService.findUsersTicket(username));
			model.addAttribute("reminder", userService.findUsersReminder(username));
			return "index";
		}
	}

	@RequestMapping("/error")
	public String error(HttpServletRequest request, Model model) {
		model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String errorMessage = null;
		if (throwable != null) {
			errorMessage = throwable.getMessage();
		}
		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect: /login";
	}

	@RequestMapping(value = "/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("error", "Nesprávné jméno nebo heslo.");
		}
		return "login";
	}

	//    @RequestMapping(value = "/attendance", method = RequestMethod.GET)
	//    public String attendancePage(HttpServletRequest request, Model model){
	//        return "attendance";
	//    }

	@RequestMapping(value = "/loginAuth")
	private String loginAuthentication() {
		return "login";
	}

}
