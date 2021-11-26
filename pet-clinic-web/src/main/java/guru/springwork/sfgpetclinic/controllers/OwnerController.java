package guru.springwork.sfgpetclinic.controllers;

import guru.springwork.sfgpetclinic.model.Owner;
import guru.springwork.sfgpetclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"/", "", "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping("/find")
    public String findOwners() {
        return "not_implemented";
    }

    @GetMapping("/{ownerId}/edit")
    public String getUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
        Owner owner = this.ownerService.findById(ownerId);
        model.addAttribute("owner", owner);
        return "owners/update";
    }


    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/details");
        mav.addObject(this.ownerService.findById(ownerId));
        return mav;
    }
}
