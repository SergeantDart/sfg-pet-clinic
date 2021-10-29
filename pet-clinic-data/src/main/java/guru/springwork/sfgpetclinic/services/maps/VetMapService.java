package guru.springwork.sfgpetclinic.services.maps;

import guru.springwork.sfgpetclinic.models.Vet;
import guru.springwork.sfgpetclinic.services.SpecialtyService;
import guru.springwork.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    @Autowired
    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public Vet save(Vet vet) {
        if(vet.getSpecialties().size() > 0) {
            vet.getSpecialties().forEach(specialty -> {
                if(specialty.getId() == null) {
                    specialty.setId(specialtyService.save(specialty).getId());
                }
            });
            return super.save(vet);
        } else {
            throw new RuntimeException("Specialisations are required !");
        }
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
