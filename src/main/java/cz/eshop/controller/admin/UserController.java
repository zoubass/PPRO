package cz.eshop.controller.admin;

import cz.eshop.dao.AuthoritiesRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.dto.UserDto;
import cz.eshop.model.Authorities;
import cz.eshop.model.AuthoritiesEnum;
import cz.eshop.model.User;
import org.apache.commons.lang.StringUtils;
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
    private UserRepository userRepo;
    @Autowired
    private AuthoritiesRepository authRepo;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(Model model, @Valid @ModelAttribute UserDto dto/*htp reques nový uživatel, nebudu mít 6 parametru, ale budu posílát celou tridu*/, BindingResult bindingResult) {
        String message = null;
        if (StringUtils.isEmpty(dto.getUser().getUsername()) || StringUtils.isEmpty(dto.getUser().getPassword())) {
            model.addAttribute("message", "Vyplňte všecha pole");
            return "admin/user";
        }
        try {

            if (bindingResult.hasErrors()) {
                return "admin/user";
            }
            dto.getAuthorities().setUsername(dto.getUser().getUsername());
            userRepo.save(dto.getUser());
            authRepo.save(dto.getAuthorities());
            message = "Uživatel úspěšně vytvořen.";
            model.addAttribute("authorities", AuthoritiesEnum.values());
        } catch (Exception e) {
            message = "Vytvoření uživatele se nezdařilo.";
        }
        model.addAttribute("message", message);
        return "admin/user";
    }

    @RequestMapping(value = "/removeUser", method = RequestMethod.GET)
    public String remove(Model model /*objekt v html vidět*/, @RequestParam String username /*parametrw kterej prijde v requestu*/) {
        Authorities a = authRepo.filterByUsername(username).get(0);
        authRepo.delete(a);
        User u = userRepo.filterByUsername(username).get(0);
        userRepo.delete(u);
        /*response v požadavku v html*/
        model.addAttribute("isValidInput", true);
        model.addAttribute("user", new User());
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("authorities", AuthoritiesEnum.values());
        return "admin/user";
    }

    @RequestMapping(value = "/showUsers", method = RequestMethod.POST /*znovu odeslani a nejsou vidět data v url*/)
    public String getUser(Model model, @ModelAttribute UserDto dto) {
        model.addAttribute("results", userRepo.filterByUsername(dto.getUser().getUsername()));
        model.addAttribute("authorities", AuthoritiesEnum.values());
        model.addAttribute("userDto", new UserDto());
        return "admin/user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String showUserForm(Model model) {
        model.addAttribute("authorities", AuthoritiesEnum.values());
        model.addAttribute("user", new User());
        model.addAttribute("userDto", new UserDto());
        return "admin/user";
    }
}
