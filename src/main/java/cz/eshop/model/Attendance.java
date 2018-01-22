package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "attendance")
public class Attendance {
    private User user;
    private Training training;
    private Date date;

    @Id
    @NotNull
    @NotEmpty
    @Column(name = "user")
    public User getUser() {
        return user;
    }

    @NotEmpty
    @NotNull
    @Column(name = "training")
    public Training getTraining() {
        return training;
    }

    @NotEmpty
    @NotNull
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
