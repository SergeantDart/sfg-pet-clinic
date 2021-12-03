package guru.springwork.sfgpetclinic.controller;

import guru.springwork.sfgpetclinic.model.Owner;
import guru.springwork.sfgpetclinic.model.Pet;
import guru.springwork.sfgpetclinic.model.PetType;
import guru.springwork.sfgpetclinic.service.OwnerService;
import guru.springwork.sfgpetclinic.service.PetService;
import guru.springwork.sfgpetclinic.service.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}/pets")
@Slf4j
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    @Autowired
    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("petTypes")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.addPets(pet);
        model.addAttribute("pet", pet);
        return "pets/form";
    }

    @PostMapping("/new")
    public String processPetCreation(Owner owner, @Valid Pet pet, BindingResult bindingResult, Model model) {
        if(StringUtils.hasLength(pet.getName())
                && pet.isNew()
                && owner.getPet(pet.getName(), true) != null) {
            bindingResult.rejectValue("name", "duplicate", "already exists");
        }
        owner.addPets(pet);
        if(bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);
            return "pets/form";
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        return "pets/form";
    }

    @PostMapping("/{petId}/edit")
    public String processPetUpdate(@PathVariable("petId") Long petId, Owner owner, @Valid Pet pet, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return "pets/form";
        } else {
            pet.setId(petId);
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
