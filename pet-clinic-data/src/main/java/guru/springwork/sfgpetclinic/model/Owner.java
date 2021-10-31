package guru.springwork.sfgpetclinic.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "owners")
public class Owner extends Person {
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

<<<<<<< HEAD
    public void addPets(Pet pet) {
        pet.setOwner(this);
        this.pets.add(pet);
=======
    public Set<Pet> getPets() {
        return pets;
    }

    public void addPets(Pet pet) {
        pet.setOwner(this);
        this.pets.add(pet);
    }
    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
>>>>>>> 9b464218a0194f536fed783cfd2eb93f39a8d30d
    }

}
