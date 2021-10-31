package guru.springwork.sfgpetclinic.repository;

import guru.springwork.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
