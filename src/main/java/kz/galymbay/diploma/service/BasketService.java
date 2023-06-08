package kz.galymbay.diploma.service;

import kz.galymbay.diploma.model.entity.Basket;
import kz.galymbay.diploma.model.entity.Clothes;
import kz.galymbay.diploma.repository.BasketRepository;
import kz.galymbay.diploma.repository.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final ClothesRepository clothesRepository;

    public Basket addClothes(Long clothesId, Basket basket) {
        Basket currentBasket = basketRepository.findById(basket.getId()).get();
        Clothes clothes = clothesRepository.findById(clothesId).get();
        currentBasket.getClothes().add(clothes);
        basketRepository.save(currentBasket);

        return currentBasket;
    }

    public Basket removeClothes(Long clothesId, Basket basket) {
        Basket currentBasket = basketRepository.findById(basket.getId()).get();
        Clothes clothes = clothesRepository.findById(clothesId).get();
        currentBasket.getClothes().remove(clothes);
        basketRepository.save(currentBasket);

        return currentBasket;
    }

    public Basket findById(Long id) {
        Basket basket = basketRepository.findById(id).orElseThrow(() -> new RuntimeException("basket not found"));
        return basket;
    }
}
