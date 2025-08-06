package com.jokerP.store.orders;

import com.jokerP.store.auth.AuthService;
import com.jokerP.store.commons.NotFoundException;
import com.jokerP.store.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var orders = orderRepository.getOrderByCustomer(user);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrder(Long orderID) {
        var order = orderRepository
                .getOrderWithItems(orderID)
                .orElseThrow(() -> new NotFoundException(Constants.Message.ORDER_NOT_FOUND));

        var user = authService.getCurrentUser();
        if (!order.isPlaceBy(user)) {
            throw new AccessDeniedException(Constants.Message.ACCESS_DENIED);
        }

        return orderMapper.toDto(order);
    }
}
