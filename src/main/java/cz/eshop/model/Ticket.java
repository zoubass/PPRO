package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="tickets")
public class Ticket {
    private User user;
    private int entry = 0;
    private Date endingDate;
    private Tariff tariff;

    @Id
    @NotNull
    @NotEmpty
    @Column(name = "user")
    public User getUser() {
        return user;
    }

    @NotEmpty
    @Column(name = "endry")
    public int getEntry() {
        return entry;
    }

    @NotEmpty
    @Column(name = "endingDate")
    public Date getEndingDate() {
        return endingDate;
    }

    @NotNull
    @NotEmpty
    @Column(name = "tarif")
    public Tariff getTariff() {
        return tariff;
    }
}
