package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.dto.UserPrincipal;
import kz.galymbay.diploma.model.entity.Client;
import kz.galymbay.diploma.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ClientService clientService;

    @GetMapping
    public String getProfile(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model){
        Client client = clientService.findById(userPrincipal.getClient().getId());
        model.addAttribute("client", client);

        return "profile";
    }
}
