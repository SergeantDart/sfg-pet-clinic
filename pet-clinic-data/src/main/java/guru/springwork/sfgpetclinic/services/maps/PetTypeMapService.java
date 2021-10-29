package guru.springwork.sfgpetclinic.services.maps;

import guru.springwork.sfgpetclinic.models.PetType;
import guru.springwork.sfgpetclinic.services.PetTypeService;

import java.util.Set;

public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {
    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public PetType save(PetType petType) {
        return super.save(petType);
    }

    @Override
    public void delete(PetType petType) {
        super.delete(petType);
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }
}