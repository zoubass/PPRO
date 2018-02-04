package cz.eshop.service;

import cz.eshop.dao.AuthoritiesRepository;
import cz.eshop.dao.ReminderRepository;
import cz.eshop.dao.TicketRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.dto.UserDto;
import cz.eshop.model.Reminder;
import cz.eshop.model.Ticket;
import cz.eshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	}

	public User saveNewlyRegisteredUser(UserDto userDto) {
		User user = saveUser(userDto);

		Ticket ticket = new Ticket();
		ticket.setEntry(1);
		ticket.setTimeTicket(false);

		ticket = ticketRepository.save(ticket);

		user.setTicket(ticket);
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

	public Ticket assignTicketToUser(Long id, Ticket ticket, Integer timeTicketDuration, Integer entryCount) {
		User user = findById(id);

		if (ticket.isTimeTicket()) {
			TimeRange timeRange = createTimeRangeForTicket(user, timeTicketDuration);

			ticket.setStartingDate(timeRange.getStartingDate());
			ticket.setEndingDate(timeRange.getEndingDate());
		} else {
			int newEntry = recountEntry(user, entryCount);
			if(newEntry == 0)
				return null;

			ticket.setEntry(newEntry);
		}
		Ticket createdTicket = ticketRepository.save(ticket);
		user.setReminder(null);
		user.setTicket(createdTicket);
		//TODO solve problem!!
		reminderRepository.delete(user.getReminder().getId());

		return ticket;
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

	private int recountEntry(User user, Integer baseCount){
		Reminder reminder = user.getReminder();
		int rCount = baseCount - reminder.getReminderCount();
		if(rCount < 0){
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
