package cz.eshop.dto;

import cz.eshop.model.Attendance;
import cz.eshop.model.Training;
import cz.eshop.model.User;

public class AttendanceDto {
    private Attendance attendance;
    private User user;
    private Training training;

    public AttendanceDto(Attendance attendance, User user, Training training) {
        this.attendance = attendance;
        this.user = user;
        this.training = training;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
