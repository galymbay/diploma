package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.entity.Client;
import kz.galymbay.diploma.model.entity.Clothes;
import kz.galymbay.diploma.repository.ClientRepository;
import kz.galymbay.diploma.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    @GetMapping
    public String getClients(Model model) {
        List<Client> clients = clientService.getClients();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @PostMapping(path = "{id}/delete")
    public String deleteClothes(@PathVariable Long id) {
        clientService.deleteClothes(id);
        return "redirect:/clients";
    }

    @PostMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        List<Client> result = clientService.search(search);

        model.addAttribute("shirtList", result);
        return "clients";
    }
}

//@RestController

