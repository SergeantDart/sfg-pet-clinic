package guru.springwork.sfgpetclinic.repository;

import guru.springwork.sfgpetclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
