package cz.db;

import cz.AbstractTest;
import cz.eshop.dao.AuthoritiesRepository;
import cz.eshop.dao.ReminderRepository;
import cz.eshop.dao.TicketRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.model.Reminder;
import cz.eshop.model.Ticket;
import cz.eshop.model.Types.BeltTypes;
import cz.eshop.model.Types.RoleTypes;
import cz.eshop.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class DatabaseTest extends AbstractTest{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReminderRepository reminderRepository;
    
    @Autowired
	private AuthoritiesRepository authRepo;
    
    @Autowired
	private TicketRepository ticketRepository;

    @Test
    public void testUserSave() {
        User user = new User();
        user.setBelt(BeltTypes.WHITE);
        user.setBornDate(new Date());
        user.setEmail("testovac@mail.com");
        user.setUsername("usernaaaame");
        user.setPassword("hesloheslo");
        user.setEnabled(true);
        user.setLastName("PosledniUnitTest");
        user.setParent(null);
        user.setRole(RoleTypes.ROLE_STRANGER);
        Reminder reminder = new Reminder();
        reminder.setReminderCount(1);
        reminder.setStartingDate(new Date());

        reminderRepository.save(reminder);

        user.setReminder(reminder);

        userRepository.save(user);

        List<User> userList = (List<User>) userRepository.findAll();

        Assert.assertEquals("usernaaaame", userList.get(0).getUsername());
    }
    
    @Test
	public void testPopulatorInsertedUsers(){
    	List<User> users = (List<User>) userRepository.findAll();
    	Assert.assertEquals(10, users.size());
	}
	
	@Test
	public void testTicketDeletionOnUser(){
		//Dotahni si uzivatele podle ID, o kterem vis, ze se vlozil, id 1 by mel byt Lukas Kebuoz
		User user = userRepository.findById(1l);
		//Ten by měl mít první ticket (s id 1) na sobe navazany, tak si to overis
		Ticket ticket = user.getTicket();
		Assert.assertNotNull(ticket);
		
		//pro jistotu si ověříš i to, že ticket je sám o sobě v tabulce ticket
		Long ticketId = ticket.getId();
		Assert.assertNotNull(ticketRepository.findOne(ticketId));
		
		//tedka ho smazes z uzivatele, tak jak chceš..a ulozíš změny
		user.setTicket(null);
		userRepository.save(user);
		
		// zbývá otestovat, zda se smazal i tiket z tabulky tiket
		Assert.assertNotNull(ticketRepository.findOne(ticketId));
	}
}
