package com.jokerP.store.mappers;

import com.jokerP.store.dtos.OrderDto;
import com.jokerP.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
