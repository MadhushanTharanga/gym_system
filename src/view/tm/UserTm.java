package view.tm;

import javafx.scene.control.ButtonBar;

public class UserTm {
    private String userId;
    private String name;
    private int contact;
    private String email;
    private ButtonBar btn;

    public UserTm() {
    }

    public UserTm(String userId, String name, int contact, String email, ButtonBar btn) {
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.btn = btn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ButtonBar getBtn() {
        return btn;
    }

    public void setBtn(ButtonBar btn) {
        this.btn = btn;
    }
}
