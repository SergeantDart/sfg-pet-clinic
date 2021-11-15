package guru.springwork.sfgpetclinic.service.map;

import guru.springwork.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    Long ownerId = 1L;
    String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        Owner o1 = new Owner();
        o1.setId(1L);
        o1.setLastName("Jerry");
        Owner o2 = new Owner();
        o2.setId(2L);
        o2.setLastName("Madonna");
        Owner o3 = new Owner();
        o3.setId(3L);
        o3.setLastName("Smith");
        ownerMapService.save(o1);
        ownerMapService.save(o2);
        ownerMapService.save(o3);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(3, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerId = 2L;
        ownerMapService.deleteById(ownerId);
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerId = 2L;
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        ownerId = 5L;
        Owner ownerToSave = new Owner();
        ownerToSave.setId(ownerId);
        Owner savedOwner = ownerMapService.save(ownerToSave);
        assertEquals(ownerId, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner ownerToSave = new Owner();
        Owner savedOwner = ownerMapService.save(ownerToSave);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
   }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        ownerId = 3L;
        Owner foundOwner = ownerMapService.findByLastName(lastName);
        assertNotNull(foundOwner);
        assertEquals(ownerId, foundOwner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        lastName = "Makangray";
        Owner foundOwner = ownerMapService.findByLastName(lastName);
        assertNull(foundOwner);
    }
}