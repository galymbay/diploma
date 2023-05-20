package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.entity.Client;
import kz.galymbay.diploma.service.ClientService;
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

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@RequestBody Client client, Model model) {
        return clientService.registration(client);
    }
}
