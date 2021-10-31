package guru.springwork.sfgpetclinic.repository;

import guru.springwork.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
