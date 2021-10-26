package guru.springwork.sfgpetclinic.controllers;

import guru.springwork.sfgpetclinic.models.Vet;
import guru.springwork.sfgpetclinic.services.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    @RequestMapping({"/vets", "vets/index", "vets/index.html"})
    public String listVets() {
        return "vets/index";
    }
}
