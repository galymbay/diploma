package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.entity.Client;
import kz.galymbay.diploma.service.ClientService;
import kz.galymbay.diploma.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final ClientService clientService;
    private final ClothesService clothesService;

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("client", new Client());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute Client client, Model model) {
        Client registration = clientService.registration(client);
//        model.addAttribute("clothesList", clothesService.getClothes());
//        model.addAttribute("clothes", new Clothes());
//        model.addAttribute("client", registration);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutDo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return "redirect:/clothes";
    }

    @GetMapping("/activate/{uuid}")
    public String activate(@PathVariable String uuid, Model model) {
        clientService.activate(uuid);

        return "login";
    }
}