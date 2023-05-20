package kz.galymbay.diploma.service;

//import kz.galymbay.diploma.model.entity.Clothes;
//import kz.galymbay.diploma.repository.ClothesRepository;
import kz.galymbay.diploma.model.entity.Clothes;
//import kz.galymbay.diploma.repository.ClothesRepository;
import kz.galymbay.diploma.repository.ClothesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClothesService {
    private final ClothesRepository clothesRepository;

    public Clothes addClothes(Clothes clothes) {
        log.info("Save clothes {} to DB", clothes);
        return clothesRepository.save(clothes);
    }

    public List<Clothes> getClothes() {
        List<Clothes> clothes = clothesRepository.findAll();
        log.info("Get all clothes from DB: {}", clothes);
        return clothes;
    }

    public Clothes updateClothes(Long id, Clothes clothes) {
        Clothes currentClothes = clothesRepository.findById(id).get();

        log.info("Update clothes with id {} to: ", id, clothes);

        currentClothes.setType(clothes.getType());
        currentClothes.setPrice(clothes.getPrice());
        currentClothes.setDescription(clothes.getDescription());

        return addClothes(currentClothes);
    }

    public String deleteClothes(Long id) {
        try {
            log.info("Delete clothes with id {}", id);
            clothesRepository.deleteById(id);
        } catch (Exception exception) {
            log.error("Clothes not found with id: {}", id);
        }

        return "SUCCESS";
    }

    @PostMapping
    public List<Clothes> search(String search) {
        List<Clothes> result = new ArrayList<>();
        List<Clothes> clothes = getClothes();

        log.info("Search clothes which contains {}", search);

        for (Clothes c : clothes) {
            if (c.getDescription().contains(search) || c.getType().contains(search) || String.valueOf(c.getPrice()).contains(search))
                result.add(c);
        }

        return result;
    }

    public Clothes findById(Long id) {
        Clothes byId = clothesRepository.findById(id).get();
        return byId;
    }
}
