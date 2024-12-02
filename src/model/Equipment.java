package model;

public class Equipment {
    private String eId;
    private String title;
    private String description;

    public Equipment() {
    }

    public Equipment(String eId, String title, String description) {
        this.eId = eId;
        this.title = title;
        this.description = description;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
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

    @Override
    public String toString() {
        return "Equipment{" +
                "eId='" + eId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
