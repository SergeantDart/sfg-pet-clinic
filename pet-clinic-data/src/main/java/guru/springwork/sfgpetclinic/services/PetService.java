package guru.springwork.sfgpetclinic.services;

import guru.springwork.sfgpetclinic.models.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> finaAll();
}
