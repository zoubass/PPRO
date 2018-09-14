package cz.eshop.service;

import cz.eshop.dao.TicketRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private UserRepository userRepository;

    public void removeTicketByUser(User user){
        User eddUser = user;
        Long ticketId = eddUser.getId();
        eddUser.setTicket(null);
        userRepository.save(eddUser);
        ticketRepo.delete(ticketId);
    }
}
