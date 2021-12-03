package guru.springwork.sfgpetclinic.repository;

import guru.springwork.sfgpetclinic.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

<<<<<<< HEAD
    Optional<Owner> findByLastName(String lastName);

    List<Owner>findAllByLastNameLike(String lastName);
=======
    Owner findByLastName(String lastName);
>>>>>>> d97dfa6d6fe24ede4d35c1c4a2d088546917cc6c
}
