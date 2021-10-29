package guru.springwork.sfgpetclinic.models;

public class Specialty extends BaseEntity {

    private String title;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
