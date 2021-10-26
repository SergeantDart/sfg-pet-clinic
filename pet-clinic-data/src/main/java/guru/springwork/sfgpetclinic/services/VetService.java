package guru.springwork.sfgpetclinic.services;
import guru.springwork.sfgpetclinic.models.Vet;

import java.util.Set;

public interface VetService {

    Vet findByLastName(String lastName);

    Vet findById(Long id);

    Vet save(Vet owner);

    Set<Vet> findAll();

}
