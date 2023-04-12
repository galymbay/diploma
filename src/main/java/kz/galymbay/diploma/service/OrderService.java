package kz.galymbay.diploma.service;

import kz.galymbay.diploma.model.entity.Order;
import kz.galymbay.diploma.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrder() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Long id, Order order) {
        Order currentClothes = orderRepository.findById(id).get();

        return addOrder(order);
    }

    public String deleteOrder(Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception exception) {

        }

        return "SUCCESS";
    }
}
