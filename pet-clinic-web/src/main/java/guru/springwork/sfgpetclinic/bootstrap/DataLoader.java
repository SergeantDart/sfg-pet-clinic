package guru.springwork.sfgpetclinic.bootstrap;

import guru.springwork.sfgpetclinic.models.Owner;
import guru.springwork.sfgpetclinic.models.Vet;
import guru.springwork.sfgpetclinic.services.OwnerService;
import guru.springwork.sfgpetclinic.services.VetService;
import guru.springwork.sfgpetclinic.services.maps.OwnerMapService;
import guru.springwork.sfgpetclinic.services.maps.VetMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner o1 = new Owner();
        o1.setId(1L);
        o1.setFirstName("Michael");
        o1.setLastName("Weston");
        ownerService.save(o1);

        Owner o2 = new Owner();
        o2.setId(2L);
        o2.setFirstName("Fiona");
        o2.setLastName("Glance");
        ownerService.save(o2);

        System.out.println("Loaded owners...");

        Vet v1 = new Vet();
        v1.setId(1L);
        v1.setFirstName("Sam");
        v1.setLastName("Axe");
        vetService.save(v1);

        Vet v2 = new Vet();
        v2.setId(2L);
        v2.setFirstName("Dorothy");
        v2.setLastName("Hawthorn");
        vetService.save(v2);

        System.out.println("Loaded vets...");

    }
}
