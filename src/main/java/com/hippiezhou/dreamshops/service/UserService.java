package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.model.User;
import com.hippiezhou.dreamshops.request.UserCreateRequest;
import com.hippiezhou.dreamshops.request.UserUpdateRequest;

public interface UserService {
    User getUserById(Long userId);

    User createUser(UserCreateRequest request);

    User updateUser(Long userId, UserUpdateRequest request);

    void deleteUser(Long userId);
}

