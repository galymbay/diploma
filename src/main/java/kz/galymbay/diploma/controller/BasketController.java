package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.dto.UserPrincipal;
import kz.galymbay.diploma.model.entity.Basket;
import kz.galymbay.diploma.model.entity.Clothes;
import kz.galymbay.diploma.repository.ClothesRepository;
import kz.galymbay.diploma.service.BasketService;
import kz.galymbay.diploma.service.ClientService;
import kz.galymbay.diploma.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;
    private final ClothesService clothesService;

    @GetMapping
    public String getBasket(Model model) {
        return "basket";
    }

    @PostMapping("/{id}")
    public String addClothes(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam Long clothes, Model model) {
        basketService.addClothes(clothes, userPrincipal.getClient().getBasket());
        model.addAttribute("clothesList", clothesService.getClothes());
        model.addAttribute("clothes", new Clothes());

        return "redirect:/clothes";
    }
}
