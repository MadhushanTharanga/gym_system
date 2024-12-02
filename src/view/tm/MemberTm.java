package view.tm;

public class MemberTm {
    private String id;
    private String name;

    public MemberTm() {
    }

    public MemberTm(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MemberTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
