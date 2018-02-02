package cz.eshop.service;

import cz.eshop.dao.ReminderRepository;
import cz.eshop.dao.TicketRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.model.Reminder;
import cz.eshop.model.Ticket;
import cz.eshop.model.Training;
import cz.eshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttendanceService attendanceService;

    public List<User> doReminder(List<Long> userIdList, Training actualTraining) {

        List<User> reminders = new ArrayList<>();
        for (Long userId : userIdList) {
            User user = userRepository.findById(userId);

            if (attendanceService.findAttendance(userId, actualTraining.getId()) != null)
                continue;

            Ticket ticket = user.getTicket();
            Reminder reminder = user.getReminder();

            if(ticket != null){
                if (isTicketValid(ticket))
                    continue;

                user.setTicket(null);
                ticketRepository.delete(ticket);
            }
            userRepository.save(checkReminder(reminder, user));
            reminders.add(user);
        }
        return reminders;
    }

    private boolean isTicketValid(Ticket ticket) {

        if (ticket.isTimeTicket()) {
            Date now = new Date();
            return now.before(ticket.getEndingDate());
        } else {
            if (ticket.getEntry() <= 0)
                return false;

            ticket.setEntry(ticket.getEntry() - 1);
            ticketRepository.save(ticket);
            return true;
        }
    }

    private User checkReminder(Reminder reminder, User user){

        if(reminder == null){
            reminder = new Reminder(1 ,new Date());
        }else {
            reminder.setReminderCount(reminder.getReminderCount() + 1);
        }
        Reminder createdReminder = reminderRepository.save(reminder);
        user.setReminder(createdReminder);

        return user;
    }
}
