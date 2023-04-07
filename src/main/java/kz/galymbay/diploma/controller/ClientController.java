package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.entity.Clothes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Controller
public class ClientController {
    @GetMapping
    public String getClients(Model model) {
        model.addAttribute("shirtList", clothes);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        List<Clothes> result = new ArrayList<>();
        for (Clothes clothe : clothes) {
            if (clothe.getDescription().contains(search))
                result.add(clothe);
        }

        model.addAttribute("shirtList", result);
        return "index";
    }
}
