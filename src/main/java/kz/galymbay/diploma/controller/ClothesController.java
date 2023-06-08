package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.dto.UserPrincipal;
import kz.galymbay.diploma.model.entity.Clothes;
import kz.galymbay.diploma.service.BasketService;
import kz.galymbay.diploma.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClothesController {
    private final ClothesService clothesService;
    private final BasketService basketService;

//    @GetMapping
//    public String getClothes(Model model) {
//        model.addAttribute("clothesList", clothesService.getClothes());
//        model.addAttribute("clothes", new Clothes());
//
//        return "index";
//    }
    @GetMapping
    public String getClothes(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size, Model model) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Clothes> clothes = clothesService.getClothesPages(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("clothesList", clothes);
        model.addAttribute("clothes", new Clothes());

        int totalPages = clothes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        if (Objects.nonNull(userPrincipal)) {
            model.addAttribute("client", userPrincipal.getClient());
            if (Objects.nonNull(userPrincipal.getClient().getBasket()))
                model.addAttribute("basket", basketService.findById(userPrincipal.getClient().getBasket().getId()));
        }

        return "index";
    }

    @PostMapping
    public String getClothes(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam String search, Model model) {
        if (!Objects.equals(search, "")) {
            List<Clothes> result = clothesService.search(search);
            model.addAttribute("clothesList", result);
        } else {
            model.addAttribute("clothesList", clothesService.getClothes());
        }
        model.addAttribute("clothesList", clothesService.checkSearch(search, userPrincipal));
        if (Objects.nonNull(userPrincipal)) {
            model.addAttribute("client", userPrincipal.getClient());
            if (Objects.nonNull(userPrincipal.getClient().getBasket()))
                model.addAttribute("basket", basketService.findById(userPrincipal.getClient().getBasket().getId()));
        }
        model.addAttribute("clothes", new Clothes());

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

    public Page<Clothes> findPaginated(Pageable pageable) {
        List<Clothes> clothes = clothesService.getClothes();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Clothes> list;

        if (clothes.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, clothes.size());
            list = clothes.subList(startItem, toIndex);
        }

        Page<Clothes> bookPage
                = new PageImpl<Clothes>(list, PageRequest.of(currentPage, pageSize), clothes.size());

        return bookPage;
    }

    @RequestMapping(value = "/listBooks", method = RequestMethod.GET)
    public String listBooks(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Clothes> clothes = findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("clothes", clothes);

        int totalPages = clothes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "pagination";
    }

}
