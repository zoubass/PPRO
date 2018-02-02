package db;

import cz.eshop.dao.AuthoritiesRepository;
import cz.eshop.dao.ReminderRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.model.Authorities;
import cz.eshop.model.Reminder;
import cz.eshop.model.Types.BeltTypes;
import cz.eshop.model.Types.RoleTypes;
import cz.eshop.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/application-context.xml")
@ActiveProfiles("test")
public class DatabaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReminderRepository reminderRepository;
    
    @Autowired
	private AuthoritiesRepository authRepo;

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
}
