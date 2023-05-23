package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.entity.Client;
import kz.galymbay.diploma.model.entity.Clothes;
import kz.galymbay.diploma.service.ClientService;
import kz.galymbay.diploma.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final ClientService clientService;
    private final ClothesService clothesService;

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@RequestBody Client client, Model model) {
        Client registration = clientService.registration(client);
        model.addAttribute("clothesList", clothesService.getClothes());
        model.addAttribute("clothes", new Clothes());
        model.addAttribute("client", registration);

        return "redirect:/clothes";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
