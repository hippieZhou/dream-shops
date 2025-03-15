package com.hippiezhou.dreamshops.mapper;

import com.hippiezhou.dreamshops.dto.OrderDto;
import com.hippiezhou.dreamshops.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderDto convertToDto(Order order);
}
