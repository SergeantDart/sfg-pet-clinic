package guru.springwork.sfgpetclinic.bootstrap;

import guru.springwork.sfgpetclinic.models.Owner;
import guru.springwork.sfgpetclinic.models.Pet;
import guru.springwork.sfgpetclinic.models.PetType;
import guru.springwork.sfgpetclinic.models.Vet;
import guru.springwork.sfgpetclinic.services.OwnerService;
import guru.springwork.sfgpetclinic.services.PetService;
import guru.springwork.sfgpetclinic.services.PetTypeService;
import guru.springwork.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
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

        Pet p1 = new Pet();
        p1.setPetType(pt1);
        p1.setName("Mikey");
        p1.setBirthDate(LocalDate.now());
        petService.save(p1);

        Pet p2 = new Pet();
        p2.setPetType(pt2);
        p2.setName("Laxx");
        p2.setBirthDate(LocalDate.now());
        petService.save(p2);

        System.out.println("Loaded pets...");

        Owner o1 = new Owner();
        o1.setFirstName("Michael");
        o1.setLastName("Weston");
        o1.setAddress("1st Decebal");
        o1.setCity("Bucharest");
        o1.setTelephone("0712121212");
        o1.getPets().add(p1);
        ownerService.save(o1);

        Owner o2 = new Owner();
        o2.setFirstName("Fiona");
        o2.setLastName("Glance");
        o2.setAddress("16th Virgil Madgearu");
        o2.setCity("Botosani");
        o2.setTelephone("0799999999");
        o2.getPets().add(p2);
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
