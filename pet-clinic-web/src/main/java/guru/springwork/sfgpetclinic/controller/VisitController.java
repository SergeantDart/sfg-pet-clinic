package guru.springwork.sfgpetclinic.controller;

import guru.springwork.sfgpetclinic.model.Pet;
import guru.springwork.sfgpetclinic.model.Visit;
import guru.springwork.sfgpetclinic.service.PetService;
import guru.springwork.sfgpetclinic.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    @Autowired
    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/new")
    public String initVisitCreateForm() {
        return "pets/visits/form";
    }

    @PostMapping("/new")
    public String processVisitCreation(@PathVariable("ownerId") Long ownerId, @Valid Visit visit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pets/visits/form";
        } else {
           visitService.save(visit);
           return "redirect:/owners/" + ownerId;
        }
    }


}
