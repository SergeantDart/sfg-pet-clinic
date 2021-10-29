package guru.springwork.sfgpetclinic.bootstrap;

import guru.springwork.sfgpetclinic.models.Owner;
import guru.springwork.sfgpetclinic.models.PetType;
import guru.springwork.sfgpetclinic.models.Vet;
import guru.springwork.sfgpetclinic.services.OwnerService;
import guru.springwork.sfgpetclinic.services.PetTypeService;
import guru.springwork.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType pt1 = new PetType();
        pt1.setType("dog");
        petTypeService.save(pt1);

        PetType pt2 = new PetType();
        pt2.setType("cat");
        petTypeService.save(pt2);

        PetType pt3 = new PetType();
        pt3.setType("parrot");
        petTypeService.save(pt3);

        System.out.println("Loaded pet types...");

        Owner o1 = new Owner();
        o1.setFirstName("Michael");
        o1.setLastName("Weston");
        ownerService.save(o1);

        Owner o2 = new Owner();
        o2.setFirstName("Fiona");
        o2.setLastName("Glance");
        ownerService.save(o2);

        System.out.println("Loaded owners...");

        Vet v1 = new Vet();
        v1.setFirstName("Sam");
        v1.setLastName("Axe");
        vetService.save(v1);

        Vet v2 = new Vet();
        v2.setFirstName("Dorothy");
        v2.setLastName("Hawthorn");
        vetService.save(v2);

        System.out.println("Loaded vets...");

    }
}
