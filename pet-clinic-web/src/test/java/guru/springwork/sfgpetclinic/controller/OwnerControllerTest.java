package guru.springwork.sfgpetclinic.controller;

import guru.springwork.sfgpetclinic.model.Owner;
import guru.springwork.sfgpetclinic.service.OwnerService;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        Owner o1 = new Owner();
        o1.setId(1L);
        Owner o2 = new Owner();
        o2.setId(2L);
        owners.add(o1);
        owners.add(o2);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void testListOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attributeExists("owners"));
    }

    @Test
    void testFindOwnersForm() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void testFindReturnMany() throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        Owner owner2 = new Owner();
        owner2.setId(2L);

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(owner1, owner2));

        mockMvc.perform(get("/owners/search").param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void testFindReturnOne() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(owner));

        mockMvc.perform(get("/owners/search"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }


    @Test
    void testShowOwner() throws Exception {
        Owner owner = new Owner();
        owner.setId(3L);
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/details"));
    }

    @Test
    void initCreateOwnerForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/form"))
                .andExpect(model().attributeExists("owner"));
        verifyZeroInteractions(ownerService);
    }

    @Test
    void testCreateOwnerProcess() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.save(any())).thenReturn(owner);

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/form"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void testUpdateOwnerProcess() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.save(any())).thenReturn(owner);

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any());
    }


}