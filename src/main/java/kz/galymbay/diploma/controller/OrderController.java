package kz.galymbay.diploma.controller;

import kz.galymbay.diploma.model.entity.Order;
import kz.galymbay.diploma.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping(path = "/get")
    public ResponseEntity<List<Order>> getClothes() {
        return ResponseEntity.ok(orderService.getOrder());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Order> addClothes(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.addOrder(order));
    }

    @PutMapping(path = "{id}/update")
    public ResponseEntity<Order> updateClothes(@PathVariable Long id, @RequestBody Order order) {
        return ResponseEntity.ok(orderService.updateOrder(id, order));
    }

    @DeleteMapping(path = "{id}/delete")
    public ResponseEntity<String> deleteClothes(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }
}
