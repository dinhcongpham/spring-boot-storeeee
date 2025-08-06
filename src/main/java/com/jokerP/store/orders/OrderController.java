package com.jokerP.store.orders;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderID}")
    public OrderDto getOrderById(@PathVariable("orderID") Long orderID) {
        return orderService.getOrder(orderID);
    }
}
