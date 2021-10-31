package guru.springwork.sfgpetclinic.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pet_types")
public class PetType extends BaseEntity {

    @Column(name = "type")
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
