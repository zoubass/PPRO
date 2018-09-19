package cz.eshop.controller.admin;

import cz.eshop.model.Types.TicketPointTypes;
import cz.eshop.model.Types.TicketTimeTypes;
import cz.eshop.model.User;
import cz.eshop.service.TicketService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web")
public class TicketController {

    @Autowired
    private UserService usrService;
    @Autowired
    private TicketService ticketService;

    private List<User> searchUsersCache = new ArrayList<>();

    @RequestMapping("/ticket")
    public String showTickets(Model model) {
        List<User> usersWithTickets = usrService.findUsersWithTicket();
        usersWithTickets = ticketService.checkValidationOfTickets(usersWithTickets);
        model.addAttribute("usersTicket", usersWithTickets);
        model.addAttribute("findingUser", new User());
        model.addAttribute("filteredUser", new User());
        return "ticket";
    }

    @RequestMapping(value = "/removeTicket", method = RequestMethod.GET)
    private String removeTicket(Model model, @RequestParam Long userId) {
        User user = usrService.findById(userId);
        ticketService.removeTicketByUser(user);
        model.addAttribute("usersTicket", usrService.findUsersWithTicket());
        model.addAttribute("findingUser", new User());
        model.addAttribute("filteredUser", new User());
        return "ticket";
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.POST)
    public String searchUsers(Model model, @ModelAttribute("findingUser") User user) {
        searchUsersCache = usrService.searchUser(user.getFirstName());
        model.addAttribute("usersSearch", searchUsersCache);
        model.addAttribute("usersTicket", usrService.findUsersWithTicket());
        model.addAttribute("filteredUser", new User());
        model.addAttribute("findingUser", new User());
        return "ticket";
    }

    @RequestMapping(value = "/addTicket")
    public String addTicket(Model model, @RequestParam("userId") Long id,
                            @RequestParam(value = "timeTicket", required = false) String checkBoxValue,
                            @RequestParam(value = "timeTicketValue", required = false) TicketTimeTypes ticketTimeTypes,
                            @RequestParam(value = "pointTicketValue", required = false) TicketPointTypes ticketPointTypes) {

        ticketService.addTicketToUser(id, checkBoxValue, ticketTimeTypes, ticketPointTypes);

        searchUsersCache = ticketService.updateCacheList(searchUsersCache, id);
        model.addAttribute("usersSearch", searchUsersCache);
        model.addAttribute("usersTicket", usrService.findUsersWithTicket());
        model.addAttribute("findingUser", new User());
        model.addAttribute("filteredUser", new User());
        return "ticket";
    }

    @RequestMapping(value = "/filterTicket", method = RequestMethod.GET)
    public String filterTicket(Model model, @ModelAttribute("filteredUser") User user) {
        String name = user.getFirstName();
        if (name != "")
            model.addAttribute("usersTicket", usrService.filterUsers(name));
        else
            model.addAttribute("usersTicket", usrService.findUsersWithTicket());

        model.addAttribute("findingUser", new User());
        model.addAttribute("filteredUser", new User());
        return "ticket";
    }


}
