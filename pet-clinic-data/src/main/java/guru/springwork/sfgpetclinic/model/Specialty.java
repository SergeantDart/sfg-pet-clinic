package guru.springwork.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

}
