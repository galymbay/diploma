package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.dto.UserPrincipal;
import kz.galymbay.diploma.model.entity.Clothes;
import kz.galymbay.diploma.service.BasketService;
import kz.galymbay.diploma.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;
    private final ClothesService clothesService;

    @GetMapping
    public String getBasket(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("client", userPrincipal.getClient());
        model.addAttribute("basket", basketService.findById(userPrincipal.getClient().getBasket().getId()));

        return "basket";
    }

    @PostMapping("/{id}")
    public String addClothes(@AuthenticationPrincipal UserPrincipal userPrincipal,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size,
                             @RequestParam Long clothes, Model model) {
        basketService.addClothes(clothes, userPrincipal.getClient().getBasket());
        model.addAttribute("clothesList", clothesService.getClothes());
        model.addAttribute("clothes", new Clothes());
        model.addAttribute("client", userPrincipal.getClient());
        model.addAttribute("basket", basketService.findById(userPrincipal.getClient().getBasket().getId()));

        return "redirect:/clothes";
    }

    @DeleteMapping("/{id}")
    public String removeClothes(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam Long clothes, Model model) {
        basketService.removeClothes(clothes, userPrincipal.getClient().getBasket());
        model.addAttribute("clothesList", clothesService.getClothes());
        model.addAttribute("clothes", new Clothes());
        if (Objects.nonNull(userPrincipal)) {
            model.addAttribute("client", userPrincipal.getClient());
            if (Objects.nonNull(userPrincipal.getClient().getBasket()))
                model.addAttribute("basket", basketService.findById(userPrincipal.getClient().getBasket().getId()));
        }

        return "redirect:/clothes";
    }
}
