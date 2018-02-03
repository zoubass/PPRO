package cz.eshop.service;

import cz.eshop.dao.AuthoritiesRepository;
import cz.eshop.dao.TicketRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.dto.UserDto;
import cz.eshop.model.Reminder;
import cz.eshop.model.Ticket;
import cz.eshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

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
}
