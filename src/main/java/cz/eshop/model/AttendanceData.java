package cz.eshop.model;

import java.util.List;

public class AttendanceData {
    private Training training;
    private List<User> users;

    public Training getTraining() {
        return training;
    }

    public AttendanceData(Training training, List<User> users) {
        this.training = training;
        this.users = users;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
