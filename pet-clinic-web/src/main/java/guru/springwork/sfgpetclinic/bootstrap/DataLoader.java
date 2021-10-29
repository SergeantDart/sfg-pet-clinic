package guru.springwork.sfgpetclinic.bootstrap;

import guru.springwork.sfgpetclinic.models.*;
import guru.springwork.sfgpetclinic.services.*;
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
    private final SpecialtyService specialtyService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, PetService petService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count == 0) {
            loadData();
        }
    }


    private void loadData() {
        Specialty s1 = new Specialty();
        s1.setTitle("radiologist");
        s1.setDescription("executes and reads the xray scans");
        Specialty savedS1 = specialtyService.save(s1);

        Specialty s2 = new Specialty();
        s2.setTitle("surgery");
        s2.setDescription("manages all the surgeries");
        Specialty savedS2= specialtyService.save(s2);

        System.out.println("Loaded specialties...");

        PetType pt1 = new PetType();
        pt1.setType("dog");
        PetType savedPt1 = petTypeService.save(pt1);


        PetType pt2 = new PetType();
        pt2.setType("cat");
        PetType savedPt2 = petTypeService.save(pt2);

        PetType pt3 = new PetType();
        pt3.setType("parrot");
        PetType savedPt3 = petTypeService.save(pt3);

        System.out.println("Loaded pet types...");

        Pet p1 = new Pet();
        p1.setPetType(savedPt1);
        p1.setName("Mikey");
        p1.setBirthDate(LocalDate.now());
        Pet savedP1 = petService.save(p1);

        Pet p2 = new Pet();
        p2.setPetType(savedPt2);
        p2.setName("Laxx");
        p2.setBirthDate(LocalDate.now());
        Pet savedP2 = petService.save(p2);

        System.out.println("Loaded pets...");

        Owner o1 = new Owner();
        o1.setFirstName("Michael");
        o1.setLastName("Weston");
        o1.setAddress("1st Decebal");
        o1.setCity("Bucharest");
        o1.setTelephone("0712121212");
        o1.getPets().add(savedP1);
        ownerService.save(o1);

        Owner o2 = new Owner();
        o2.setFirstName("Fiona");
        o2.setLastName("Glance");
        o2.setAddress("16th Virgil Madgearu");
        o2.setCity("Botosani");
        o2.setTelephone("0799999999");
        o2.getPets().add(savedP2);
        ownerService.save(o2);

        System.out.println("Loaded owners...");

        Vet v1 = new Vet();
        v1.setFirstName("Sam");
        v1.setLastName("Axe");
        v1.getSpecialties().add(savedS1);
        vetService.save(v1);

        Vet v2 = new Vet();
        v2.setFirstName("Dorothy");
        v2.setLastName("Hawthorn");
        v2.getSpecialties().add(savedS2);
        vetService.save(v2);

        System.out.println("Loaded vets...");
    }
}
