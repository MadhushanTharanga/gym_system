package view.tm;

import javafx.scene.control.ButtonBar;

import java.util.Date;

public class AttendanceTm {
    private String aId;
    private Date date;
    private String user_userId;
    private ButtonBar btn;


    public AttendanceTm() {
    }
    public AttendanceTm(String aId, Date date, String user_userId, ButtonBar btn) {
        this.aId = aId;
        this.date = date;
        this.user_userId = user_userId;
        this.btn = btn;
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

    public ButtonBar getBtn() {
        return btn;
    }

    public void setBtn(ButtonBar btn) {
        this.btn = btn;
    }
}
