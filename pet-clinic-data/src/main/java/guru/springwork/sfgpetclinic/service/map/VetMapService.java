package guru.springwork.sfgpetclinic.service.map;

import guru.springwork.sfgpetclinic.model.Vet;
import guru.springwork.sfgpetclinic.service.SpecialtyService;
import guru.springwork.sfgpetclinic.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
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
        if(vet.getSpecialities().size() > 0) {
            vet.getSpecialities().forEach(specialty -> {
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
