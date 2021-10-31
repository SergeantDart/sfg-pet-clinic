package guru.springwork.sfgpetclinic.repository;

import guru.springwork.sfgpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
