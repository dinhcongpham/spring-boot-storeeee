package com.jokerP.store.controllers;

import com.jokerP.store.dtos.ErrorDto;
import com.jokerP.store.dtos.OrderDto;
import com.jokerP.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
