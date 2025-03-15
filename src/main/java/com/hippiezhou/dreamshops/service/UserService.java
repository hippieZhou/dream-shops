package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.dto.user.UserDto;
import com.hippiezhou.dreamshops.model.User;
import com.hippiezhou.dreamshops.dto.user.UserCreateRequest;
import com.hippiezhou.dreamshops.dto.user.UserUpdateRequest;

public interface UserService {
    User getUserById(Long userId);

    User createUser(UserCreateRequest request);

    User updateUser(Long userId, UserUpdateRequest request);

    void deleteUser(Long userId);

    UserDto convertToDto(User user);

    User getAuthenticatedUser();
}

