package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "reminders")
public class Reminder {
    private User user;
    private int reminderCount;
    private Date startingDate;

    @Id
    @NotEmpty
    @NotNull
    @Column(name="user")
    public User getUser() {
        return user;
    }

    @NotEmpty
    @Column(name = "reminderCount")
    public int getReminderCount() {
        return reminderCount;
    }

    @NotEmpty
    @Column(name = "startingDate")
    public Date getStartingDate() {
        return startingDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setReminderCount(int reminderCount) {
        this.reminderCount = reminderCount;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }
}
