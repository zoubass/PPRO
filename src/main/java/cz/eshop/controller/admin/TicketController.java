package cz.eshop.controller.admin;

import cz.eshop.model.User;
import cz.eshop.service.TicketService;
import cz.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web")
public class TicketController {

    @Autowired
    private UserService usrService;
    @Autowired
    private TicketService ticketService;

    @RequestMapping("/ticket")
    public String showTickets(Model model){
        model.addAttribute("usersTicket", usrService.findUsersWithTicket());
        return "ticket";
    }

    @RequestMapping(value = "/removeTicket", method = RequestMethod.GET)
    private String removeTicket(Model model, @RequestParam Long userId){
        User user = usrService.findById(userId);
        ticketService.removeTicketByUser(user);
        model.addAttribute("usersTicket",usrService.findUsersWithTicket());
        return "ticket";
    }


}
