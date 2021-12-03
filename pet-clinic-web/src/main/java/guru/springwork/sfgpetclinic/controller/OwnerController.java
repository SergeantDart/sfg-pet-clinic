package guru.springwork.sfgpetclinic.controller;

import guru.springwork.sfgpetclinic.model.Owner;
import guru.springwork.sfgpetclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;



@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/", "", "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/find";
    }

    @GetMapping("/search")
    public String searchOwnersByLastName(Owner owner, BindingResult bindingResult, Model model) {
       if(owner.getLastName() == null) {
           owner.setLastName("");
       }

       List<Owner> filteredOwners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

       if(filteredOwners.size() == 0) {
           bindingResult.rejectValue("lastName", "notFound", "Not found");
           return "owners/find";

       } else if (filteredOwners.size() == 1) {
           owner = filteredOwners.iterator().next();
           return "redirect:/owners/" + owner.getId();

       } else {
           model.addAttribute("owners", filteredOwners);
           return "owners/index";
       }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/details");
        mav.addObject(this.ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String getCreateForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/form";
    }

    @PostMapping("/new")
    public String processCreateUser(@Valid Owner owner, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "owners/form";
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{id}/edit")
    public String getUpdateForm(@PathVariable("id")Long id, Model model) {
        Owner owner = ownerService.findById(id);
        model.addAttribute("owner", owner);
        return "owners/form";
    }

    @PostMapping("/{id}/edit")
    public String processUpdateUser(@Valid Owner owner, BindingResult bindingResult, @PathVariable("id") Long id) {
        if(bindingResult.hasErrors()) {
            return "owners/form";
        } else {
            owner.setId(id);
            ownerService.save(owner);
            return "redirect:/owners/" + id;
        }
    }
}
