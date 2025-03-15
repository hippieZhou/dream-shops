package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.dto.user.*;
import com.hippiezhou.dreamshops.model.User;

public interface UserService {
    UserGetResponse getUserById(Long userId);

    UserCreateResponse createUser(UserCreateRequest request);

    UserUpdateResponse updateUser(Long userId, UserUpdateRequest request);

    void deleteUser(Long userId);

    User getAuthenticatedUser();
}

