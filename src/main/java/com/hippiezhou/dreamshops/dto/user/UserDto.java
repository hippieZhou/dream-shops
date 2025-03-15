package com.hippiezhou.dreamshops.dto.user;

import com.hippiezhou.dreamshops.dto.cart.CartDto;
import com.hippiezhou.dreamshops.dto.order.OrderDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;
}
