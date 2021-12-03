package guru.springwork.sfgpetclinic.jpa;

import guru.springwork.sfgpetclinic.model.Owner;
import guru.springwork.sfgpetclinic.repository.OwnerRepository;
import guru.springwork.sfgpetclinic.repository.PetRepository;
import guru.springwork.sfgpetclinic.repository.PetTypeRepository;
import guru.springwork.sfgpetclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
<<<<<<< HEAD:pet-clinic-data/src/main/java/guru/springwork/sfgpetclinic/service/jpa/OwnerJPAService.java
import java.util.List;
import java.util.Locale;
=======
>>>>>>> d97dfa6d6fe24ede4d35c1c4a2d088546917cc6c:pet-clinic-data/src/main/java/guru/springwork/sfgpetclinic/jpa/OwnerJPAService.java
import java.util.Set;

@Service
@Profile("jpa")
public class OwnerJPAService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    @Autowired
    public OwnerJPAService(OwnerRepository ownerRepository, PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }


    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }
}
