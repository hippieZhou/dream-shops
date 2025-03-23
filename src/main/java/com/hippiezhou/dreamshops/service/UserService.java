package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.controller.user.request.UserCreateRequest;
import com.hippiezhou.dreamshops.controller.user.request.UserUpdateRequest;
import com.hippiezhou.dreamshops.controller.user.response.UserCreateResponse;
import com.hippiezhou.dreamshops.controller.user.response.UserGetResponse;
import com.hippiezhou.dreamshops.controller.user.response.UserUpdateResponse;
import com.hippiezhou.dreamshops.model.User;

public interface UserService {
    UserGetResponse getUserById(Long userId);

    UserCreateResponse createUser(UserCreateRequest request);

    UserUpdateResponse updateUser(Long userId, UserUpdateRequest request);

    void deleteUser(Long userId);

    User getAuthenticatedUser();
}

