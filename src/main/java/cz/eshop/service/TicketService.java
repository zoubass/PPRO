package cz.eshop.service;

import cz.eshop.dao.TicketRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.model.Ticket;
import cz.eshop.model.Types.TicketPointTypes;
import cz.eshop.model.Types.TicketTimeTypes;
import cz.eshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReminderService reminderService;

    public void removeTicketByUser(User user) {
        User eddUser = user;
        Long ticketId = eddUser.getTicket().getId();
        eddUser.setTicket(null);
        userRepository.save(eddUser);
        ticketRepo.delete(ticketId);
    }

    public void addTicketToUser(Long userId, String isTimeTicket, TicketTimeTypes ticketTimeTypes, TicketPointTypes ticketPointTypes) {
        User user = userRepository.findById(userId);

        if (isTimeTicket(isTimeTicket)) {
            addTimeTicket(user, ticketTimeTypes);
        } else {
            addPointTicket(user, ticketPointTypes);
        }
    }

    public List<User> updateCacheList(List<User> cacheList, Long userId) {
        List<User> updatedList = new ArrayList<>();
        for (User user : cacheList
                ) {
            if (user.getId() == userId)
                continue;
            updatedList.add(user);
        }
        return updatedList;
    }

    public List<User> checkValidationOfTickets(List<User> usersWithTickets) {
        List<User> usersToRemove = new ArrayList<>();
        for (User user : usersWithTickets) {
            Ticket usersTicket = user.getTicket();
            if (reminderService.isTicketValid(usersTicket, false))
                continue;

            usersToRemove.add(user);
            user.setTicket(null);
            userRepository.save(user);
            ticketRepo.delete(usersTicket.getId());
        }
        usersWithTickets.removeAll(usersToRemove);

        return usersWithTickets;
    }

    private void addTimeTicket(User user, TicketTimeTypes ticketTimeTypes) {
        Ticket ticket = new Ticket();

        ticket.setTimeTicket(true);
        ticket.setStartingDate(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, ticketTimeTypes.Number());
        Date dateAfterTime = calendar.getTime();
        ticket.setEndingDate(dateAfterTime);

        ticketRepo.save(ticket);
        user.setTicket(ticket);
        userRepository.save(user);
    }

    private void addPointTicket(User user, TicketPointTypes ticketPointTypes) {
        Ticket ticket = new Ticket();

        ticket.setTimeTicket(false);
        ticket.setEntry(ticketPointTypes.Number());

        ticketRepo.save(ticket);
        user.setTicket(ticket);
        userRepository.save(user);
    }

    private boolean isTimeTicket(String value) {
        return value != null;
    }
}
