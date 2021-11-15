package guru.springwork.sfgpetclinic.jpa;

import guru.springwork.sfgpetclinic.model.Visit;
import guru.springwork.sfgpetclinic.repository.VisitRepository;
import guru.springwork.sfgpetclinic.service.CrudService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class VisitJPAService implements CrudService<Visit, Long> {

    private final VisitRepository visitRepository;

    public VisitJPAService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
