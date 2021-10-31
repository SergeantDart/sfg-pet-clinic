package guru.springwork.sfgpetclinic.repository;

import guru.springwork.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
