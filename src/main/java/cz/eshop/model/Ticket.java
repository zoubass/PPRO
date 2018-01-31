package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(mappedBy = "ticket")
    private User user;
    private int entry = 0;
    private Date startingDate;
    private Date endingDate;
    private boolean isTimeTicket;


    //region getters

    @NotNull
    @Column(name = "ticket_id", nullable = false)
    public long getId() {
        return id;
    }

    @NotNull
    @Column(name = "user_id")
    public User getUser() {
        return user;
    }

    @NotNull
    @Column(name = "endry")
    public int getEntry() {
        return entry;
    }

    @Column(name = "endingDate")
    public Date getEndingDate() {
        return endingDate;
    }

    @Column(name = "startingDate")
    public Date getStartingDate() {
        return startingDate;
    }

    @NotNull
    @Column(name = "isTimeTicket")
    public boolean isTimeTicket() {
        return isTimeTicket;
    }
    //endregion


    //region setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public void setTimeTicket(boolean timeTicket) {
        isTimeTicket = timeTicket;
    }

    //endregion
}
