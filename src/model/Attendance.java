package model;

import java.util.Date;

public class Attendance {
    private String aId;
    private Date date;
    private String user_userId;

    public Attendance() {
    }

    public Attendance(String aId, Date date, String user_userId) {
        this.aId = aId;
        this.date = date;
        this.user_userId = user_userId;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser_userId() {
        return user_userId;
    }

    public void setUser_userId(String user_userId) {
        this.user_userId = user_userId;
    }
}
