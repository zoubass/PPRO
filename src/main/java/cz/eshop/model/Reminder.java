package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "reminders")
public class Reminder {
    @Id
    @GeneratedValue
    private long id;

    private int reminderCount;
    private Date startingDate;

    public Reminder(){}

    public Reminder(int reminderCount, Date startingDate) {
        this.reminderCount = reminderCount;
        this.startingDate = startingDate;
    }

    //region getters

    @NotNull
    @Column(name="reminder_id", nullable = false)
    public long getId() {
        return id;
    }


    @Column(name = "reminderCount")
    public int getReminderCount() {
        return reminderCount;
    }


    @Column(name = "startingDate")
    public Date getStartingDate() {
        return startingDate;
    }
    //endregion

    //region setters
    public void setReminderCount(int reminderCount) {
        this.reminderCount = reminderCount;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

	public void setId(long id) {
		this.id = id;
	}

	//endregion
}
