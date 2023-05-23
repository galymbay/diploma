package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.dto.UserPrincipal;
import kz.galymbay.diploma.model.entity.Clothes;
import kz.galymbay.diploma.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClothesController {
    private final ClothesService clothesService;

    @GetMapping
    public String getClothes(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
//        if (search != null) {
//            List<Clothes> result = clothesService.search(search);
//            model.addAttribute("clothesList", result);
//            model.addAttribute("clothes", new Clothes());
//        } else {
            model.addAttribute("clothesList", clothesService.getClothes());
            model.addAttribute("clothes", new Clothes());
            model.addAttribute("client", userPrincipal.getClient());
//        }
        return "index";
    }

    @PostMapping
    public String getClothes(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam String search, Model model) {
        if (!Objects.equals(search, "")) {
            List<Clothes> result = clothesService.search(search);
            model.addAttribute("clothesList", result);
            model.addAttribute("clothes", new Clothes());
            model.addAttribute("client", userPrincipal.getClient());
        } else {
            model.addAttribute("clothesList", clothesService.getClothes());
            model.addAttribute("clothes", new Clothes());
        }

        return "index";
    }

    @GetMapping("/{id}")
    public String getClothesById(@PathVariable Long id, Model model) {
        Clothes clothes = clothesService.findById(id);
        model.addAttribute("updateClothes", clothes);
        return "index";
    }

    @PostMapping(path = "/add")
    public String addClothes(@ModelAttribute Clothes clothes, @RequestParam("image") MultipartFile image) {
        clothesService.addClothes(clothes, image);
        return "redirect:/clothes";
    }

    @PostMapping(path = "{id}/update")
    public String updateClothes(@PathVariable Long id, @ModelAttribute Clothes clothes, @RequestParam("image") MultipartFile image) {
        clothesService.updateClothes(id, clothes, image);
        return "redirect:/clothes";
    }

    @PostMapping(path = "{id}/delete")
    public String deleteClothes(@PathVariable Long id) {
        clothesService.deleteClothes(id);
        return "redirect:/clothes";
    }
}
