package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.entity.Client;
import kz.galymbay.diploma.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final ClientService clientService;

//    @GetMapping
//    public String registration(Model model) {
//        model.addAttribute("client", new Client());
//        return "registration";
//    }

    @PostMapping
    public ResponseEntity<Client> registration(@RequestBody Client client) {
        return ResponseEntity.ok().body(clientService.saveClient(client));
    }

    @PostMapping
    @PreAuthorize()
    public ResponseEntity<Client> registration(@RequestBody Client client) {
        return ResponseEntity.ok().body(clientService.saveClient(client));
    }
}
