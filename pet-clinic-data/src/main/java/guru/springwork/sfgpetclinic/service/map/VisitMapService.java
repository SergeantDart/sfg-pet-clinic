package guru.springwork.sfgpetclinic.service.map;

import guru.springwork.sfgpetclinic.model.Visit;
import guru.springwork.sfgpetclinic.service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("map")
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    private Set<Visit> visits = new HashSet<>();

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public Visit save(Visit visit) {
        if(visit.getPet() == null ||
                visit.getPet().getOwner() == null ||
                visit.getPet().getId() == null) {
            throw new RuntimeException("Invalid visit data !");
        }
        return super.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
