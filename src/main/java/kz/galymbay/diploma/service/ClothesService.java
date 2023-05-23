package kz.galymbay.diploma.service;

//import kz.galymbay.diploma.model.entity.Clothes;
//import kz.galymbay.diploma.repository.ClothesRepository;

import kz.galymbay.diploma.model.entity.Clothes;
//import kz.galymbay.diploma.repository.ClothesRepository;
import kz.galymbay.diploma.repository.ClothesRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClothesService {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
    private final ClothesRepository clothesRepository;

    @SneakyThrows
    public Clothes addClothes(Clothes clothes, MultipartFile image) {
        log.info("Save clothes {} to DB", clothes);
        clothes.setUrl(saveImage(image));

        return clothesRepository.save(clothes);
    }

    public List<Clothes> getClothes() {
        List<Clothes> clothes = clothesRepository.findAll();
        log.info("Get all clothes from DB: {}", clothes);
        return clothes;
    }

    public Clothes updateClothes(Long id, Clothes clothes, MultipartFile image) {
        Clothes currentClothes = clothesRepository.findById(id).get();

        log.info("Update clothes with id {} to: ", id, clothes);

        currentClothes.setType(clothes.getType());
        currentClothes.setPrice(clothes.getPrice());
        currentClothes.setDescription(clothes.getDescription());
        if (Objects.nonNull(image)) {
            currentClothes.setUrl(saveImage(image));
        }

        return clothesRepository.save(currentClothes);
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

    @SneakyThrows
    public String saveImage(MultipartFile image) {
        StringBuilder fileName = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, image.getOriginalFilename());
        fileName.append(image.getOriginalFilename());
        Files.write(fileNameAndPath, image.getBytes());

        return fileName.toString();
    }
}
