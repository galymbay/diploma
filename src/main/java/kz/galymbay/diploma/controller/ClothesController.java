package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.entity.Clothes;
import kz.galymbay.diploma.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClothesController {
    private final ClothesService clothesService;

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public String getClothes(String search, Model model) {
        if (search != null) {
            List<Clothes> result = clothesService.search(search);
            model.addAttribute("clothesList", result);
            model.addAttribute("clothes", new Clothes());
        } else {
            model.addAttribute("clothesList", clothesService.getClothes());
            model.addAttribute("clothes", new Clothes());
        }
//        return ResponseEntity.ok(clothesService.getClothes());
        return "index";
    }

    @PostMapping(path = "/add")
    public String addClothes(@ModelAttribute Clothes clothes) {
        clothesService.addClothes(clothes);
        return "redirect:/clothes";
    }

    @PutMapping(path = "{id}/update")
    public ResponseEntity<Clothes> updateClothes(@PathVariable Long id, @RequestBody Clothes clothes) {
        return ResponseEntity.ok(clothesService.updateClothes(id, clothes));
    }

    @PostMapping(path = "{id}/delete")
    public String deleteClothes(@PathVariable Long id) {
        clothesService.deleteClothes(id);
        return "redirect:/clothes";
    }

    @PostMapping
    public String search(@ModelAttribute String search, Model model) {
        List<Clothes> result = clothesService.search(search);
        model.addAttribute("clothesList", result);
        return "index";
    }
}
