package guru.springwork.sfgpetclinic.jpa;

import guru.springwork.sfgpetclinic.model.Owner;
import guru.springwork.sfgpetclinic.repository.OwnerRepository;
import guru.springwork.sfgpetclinic.repository.PetRepository;
import guru.springwork.sfgpetclinic.repository.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJPAServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerJPAService ownerJPAService;

    String lastName = "Smith";
    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = new Owner();
        returnOwner.setId(1L);
    }

    @Test
    void findAll() {
        Owner o1 = new Owner();
        o1.setLastName("George");
        Owner o2 = new Owner();
        o2.setLastName("Michael");
        Set<Owner> returnOwners = new HashSet<>();
        returnOwners.add(o1);
        returnOwners.add(o2);
        when(ownerRepository.findAll()).thenReturn(returnOwners);
        Set<Owner> owners = ownerJPAService.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = ownerJPAService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = new Owner();
        ownerToSave.setId(1L);
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = ownerJPAService.save(ownerToSave);
        assertNotNull(savedOwner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = ownerJPAService.findById(1L);
        assertNull(owner);
    }

    @Test
    void delete() {
        ownerJPAService.delete(returnOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerJPAService.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }
}