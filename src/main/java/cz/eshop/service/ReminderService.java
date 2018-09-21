package cz.eshop.service;

import cz.eshop.dao.AttendanceRepository;
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
    private AttendanceRepository attendanceRepository;

    /**
     * Method do all operation required to reminder process. Checked all users and decide if user have reminder
     * @param userIdList checking users
     * @param actualTraining actual training
     * @return list of users with reminder
     */
    public List<User> doReminder(List<Long> userIdList, Training actualTraining) {
        List<User> reminders = new ArrayList<>();
        for (Long userId : userIdList) {
            User user = userRepository.findById(userId);

            if (attendanceRepository.filterByTrainingAndUser(actualTraining,user) != null)
                continue;

            Ticket ticket = user.getTicket();
            Reminder reminder = user.getReminder();

            if (ticket != null) {
                //if ticked valid deduct one point and go to next user
                if (isTicketValid(ticket, true))
                    continue;

                //if not valid remove old ticket
                user.setTicket(null);
                userRepository.save(user);
                ticketRepository.delete(ticket);
            }
            //get reminder to user
            userRepository.save(addOnePointToReminder(reminder, user));
            reminders.add(user);
        }
        return reminders;
    }

    /**
     * For paying all reminders of user
     * @param user user with Reminder
     */
    public void payReminder(User user) {
        User eddUser = user;
        Long reminderId = eddUser.getReminder().getId();
        eddUser.setReminder(null);
        userRepository.save(eddUser);
        reminderRepository.delete(reminderId);
    }

    /**
     * Method checked if is time ticket or point ticket valid. There is option for deduct one point. This is common opp during signing user to Training
     * @param ticket current ticket
     * @param deductOnePoint if we need deduct one point from point ticket
     * @return true = is Valid, false = isn't valid
     */
    public boolean isTicketValid(Ticket ticket, boolean deductOnePoint) {

        if (ticket.isTimeTicket()) {
            Date now = new Date();
            return now.before(ticket.getEndingDate());
        } else {
            if (ticket.getEntry() <= 0)
                return false;

            if (deductOnePoint) {
                ticket.setEntry(ticket.getEntry() - 1);
                ticketRepository.save(ticket);
            }

            return true;
        }
    }

    /**
     * Method ether create new Reminder to DB or add one point to existing reminder
     * @param reminder current reminder of User
     * @param user current User
     * @return user with new/edit reminder
     */
    private User addOnePointToReminder(Reminder reminder, User user) {

        if (reminder == null) {
            reminder = new Reminder(1, new Date());
        } else {
            reminder.setReminderCount(reminder.getReminderCount() + 1);
        }
        Reminder createdReminder = reminderRepository.save(reminder);
        user.setReminder(createdReminder);

        return user;
    }
}
