package guru.springwork.sfgpetclinic.services.maps;

import guru.springwork.sfgpetclinic.models.Owner;
import guru.springwork.sfgpetclinic.models.Pet;
import guru.springwork.sfgpetclinic.services.OwnerService;
import guru.springwork.sfgpetclinic.services.PetService;
import guru.springwork.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    @Autowired
    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public Owner save(Owner owner) {
        if(owner != null) {
            if(owner.getPets() != null) {
                owner.getPets().forEach(pet -> {
                    if(pet != null) {
                        if(pet.getPetType() != null) {
                            if(pet.getPetType().getId() == null) {
                                pet.setPetType(petTypeService.save(pet.getPetType()));
                            }
                        } else {
                            throw new RuntimeException("Pet type is required !");
                        }

                        if(pet.getId() == null) {
                            Pet savedPet = petService.save(pet);
                            pet.setId(savedPet.getId());
                        }
                    } else {
                        throw new RuntimeException("Pet is required !");
                    }
                });
            }
            return super.save(owner);
        } else {
            return null;
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
