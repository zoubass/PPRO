package cz.eshop.dto;

import cz.eshop.model.User;

public class CmprAttendanceDto {
    private User user;
    private int entry;

    public CmprAttendanceDto(User user, int entry){
        this.user = user;
        this.entry = entry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }
}
