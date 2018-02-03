package cz.eshop.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
	private User user;

    @OneToOne
    @JoinColumn(name = "training_id")
    private Training training;

    public Attendance(){}

    public Attendance(User user, Training training) {
        this.user = user;
        this.training = training;
    }

    //region getters

    @NotNull
    @Column(name="attendance_id", nullable = false)
    public long getId() {
        return id;
    }

    @NotNull

    @Column(name = "user_id")
    public User getUser() {
        return user;
    }



    @NotNull
    @Column(name = "training_id")
    public Training getTraining() {
        return training;
    }
    //endregion

    //region setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
    //endregion
}
