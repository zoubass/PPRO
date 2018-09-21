package cz.eshop.service;

import cz.eshop.dao.*;
import cz.eshop.dto.UserDto;
import cz.eshop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public User saveUser(UserDto userDto) {
        userDto.getAuthorities().setUsername(userDto.getUser().getUsername());
        User user = userRepository.save(userDto.getUser());
        authoritiesRepository.save(userDto.getAuthorities());
        return user;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void removeUser(Long id) {
        attendanceService.removeAttendance(userRepository.findById(id));
        userRepository.delete(id);
    }

    public boolean isNotUniqueUsername(UserDto userDto) {
        return userRepository.findByUsername(userDto.getUser().getUsername()) != null;
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void editUser(UserDto userDto) {
        userRepository.save(userDto.getUser());
        //TODO test if save authorities don't missing
    }

    /**
     * Method for editing users. Carry all attributes of DTO without null made by model
     *
     * @param userDto     - new DTO edited from form
     * @param prevUserDto - old DTO found in DB by ID
     */
    @Transactional
    public void editUser(UserDto userDto, UserDto prevUserDto) {
        User newUser = userDto.getUser();
        newUser.setReminder(prevUserDto.getUser().getReminder());
        newUser.setTicket(prevUserDto.getUser().getTicket());

        Authorities newAuth = prevUserDto.getAuthorities();
        newAuth.setAuthority(userDto.getAuthorities().getAuthority());

        UserDto newUserDto = new UserDto(newUser, newAuth);
        userRepository.save(newUserDto.getUser());
        authoritiesRepository.save(newUserDto.getAuthorities());
    }

    public User saveNewlyRegisteredUser(UserDto userDto) {

        Ticket ticket = new Ticket();
        ticket.setEntry(1);
        ticket.setTimeTicket(false);

        ticket = ticketRepository.save(ticket);

        userDto.getUser().setTicket(ticket);

        User user = saveUser(userDto);
        return user;
    }

    public List<User> findUsersWithReminder() {
        return userRepository.findUserByReminderNotNull();
    }

    public Reminder findUsersReminder(String username) {
        return userRepository.findByUsername(username).getReminder();
    }

    public Ticket findUsersTicket(String username) {
        return userRepository.findByUsername(username).getTicket();
    }

    public List<User> findUsersWithTicket() {
        return userRepository.findUsersByTicketNotNull();
    }

    /**
     * Method serve to assign ticket to user. depending on the previous reminders user has had
     *
     * @param userId             - user id
     * @param ticket             - used to determined ticket type (time | entry count)
     * @param timeTicketDuration - number of days the time ticket has to be set to
     * @param entryCount         - number of entries the entry ticket has to be set to
     * @return
     */
    public Ticket assignTicketToUser(Long userId, Ticket ticket, Integer timeTicketDuration, Integer entryCount) {
        User user = findById(userId);

        if (ticket.isTimeTicket()) {
            TimeRange timeRange = createTimeRangeForTicket(user, timeTicketDuration);

            ticket.setStartingDate(timeRange.getStartingDate());
            ticket.setEndingDate(timeRange.getEndingDate());
        } else {
            int newEntry = recountEntry(user, entryCount);
            if (newEntry == 0)
                return null;

            ticket.setEntry(newEntry);
        }
        Ticket createdTicket = ticketRepository.save(ticket);
        user.setTicket(createdTicket);
        Long reminderId = user.getReminder() != null ? user.getReminder().getId() : null;

        user.setReminder(null);
        userRepository.save(user);

        if (reminderId != null) {
            reminderRepository.delete(reminderId);
        }


        return ticket;
    }

    public List<User> searchUser(String name) {
        return userRepository.getUsersByNameWithoutTicket(name);
    }

    public List<User> filterUsers(String name) {
        return userRepository.getUsersByNameWithTicket(name);
    }

    public List<User> getUsersOnCurrentTraining(Training training){
        List<User> usersOnCurrentTraining = new ArrayList<>();
        List<Attendance> currAttendance = attendanceRepository.filterByTraining(training);
        for(Attendance att : currAttendance){
            usersOnCurrentTraining.add(att.getUser());
        }
        return usersOnCurrentTraining;
    }

    public List<User> getUsersForPickOnTraining(List<User> signedUsers){
        List<User> allUsers = userRepository.findUsersByIdNotNull();
        allUsers.removeAll(signedUsers);
        return allUsers;
    }

    private TimeRange createTimeRangeForTicket(User user, Integer timeTicketDuration) {
        Calendar calendar = Calendar.getInstance();
        Date startingDate;

        if (user.getReminder() != null) {
            startingDate =
                    user.getReminder().getStartingDate() != null ? user.getReminder().getStartingDate() : new Date();
        } else {
            startingDate = new Date();
        }

        calendar.setTime(startingDate);
        calendar.add(Calendar.DAY_OF_YEAR, timeTicketDuration);

        return new TimeRange(startingDate, calendar.getTime());
    }

    private int recountEntry(User user, Integer baseCount) {
        Reminder reminder = user.getReminder();
        int rCount = baseCount - reminder.getReminderCount();
        if (rCount < 0) {
            reminder.setReminderCount(rCount * (-1));
            reminderRepository.save(reminder);
            rCount = 0;
        }

        return rCount;
    }

    /**
     * Helper method used to transfer whole date interval for the time ticket
     */
    protected class TimeRange {
        private Date startingDate;
        private Date endingDate;

        public TimeRange(Date startingDate, Date endingDate) {
            this.startingDate = startingDate;
            this.endingDate = endingDate;
        }

        public Date getStartingDate() {
            return startingDate;
        }

        public void setStartingDate(Date startingDate) {
            this.startingDate = startingDate;
        }

        public Date getEndingDate() {
            return endingDate;
        }

        public void setEndingDate(Date endingDate) {
            this.endingDate = endingDate;
        }
    }
}
