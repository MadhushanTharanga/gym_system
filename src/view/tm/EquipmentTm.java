package view.tm;

import javafx.scene.control.ButtonBar;

public class EquipmentTm {
    private String id;
    private String title;
    private String description;
    private ButtonBar btn;

    public EquipmentTm(String id, String title, String description, ButtonBar btn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ButtonBar getBtn() {
        return btn;
    }

    public void setBtn(ButtonBar btn) {
        this.btn = btn;
    }

    public EquipmentTm() {
    }
}
