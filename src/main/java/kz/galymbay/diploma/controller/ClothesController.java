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
    public String getClothes(Model model) {
        model.addAttribute("clothesList", clothesService.getClothes());
//        return ResponseEntity.ok(clothesService.getClothes());
        return "index";
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Clothes> addClothes(@RequestBody Clothes clothes) {
        return ResponseEntity.ok(clothesService.addClothes(clothes));
    }

    @PutMapping(path = "{id}/update")
    public ResponseEntity<Clothes> updateClothes(@PathVariable Long id, @RequestBody Clothes clothes) {
        return ResponseEntity.ok(clothesService.updateClothes(id, clothes));
    }

    @DeleteMapping(path = "{id}/delete")
    public ResponseEntity<String> deleteClothes(@PathVariable Long id) {
        return ResponseEntity.ok(clothesService.deleteClothes(id));
    }

    @PostMapping
    public String search(@RequestParam("search") String search, Model model) {
        List<Clothes> result = clothesService.search(search);
        model.addAttribute("clothesList", result);
        return "index";
    }
}
