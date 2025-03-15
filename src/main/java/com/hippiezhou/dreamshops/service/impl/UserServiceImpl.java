package com.hippiezhou.dreamshops.service.impl;

import com.hippiezhou.dreamshops.dto.user.*;
import com.hippiezhou.dreamshops.exception.ResourceAlreadyExistsException;
import com.hippiezhou.dreamshops.exception.ResourceNotFoundException;
import com.hippiezhou.dreamshops.mapper.UserMapper;
import com.hippiezhou.dreamshops.model.User;
import com.hippiezhou.dreamshops.repository.UserRepository;
import com.hippiezhou.dreamshops.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserGetResponse getUserById(Long userId) {
        return userRepository.findById(userId)
            .map(userMapper::toGetResponse)
            .orElseThrow(() -> new ResourceNotFoundException(userId));
    }

    @Override
    public UserCreateResponse createUser(UserCreateRequest request) {
        return Optional.of(request)
            .filter(user -> !userRepository.existsByEmail(user.email()))
            .map(req -> {
                User user = new User();
                user.setFirstName(req.firstName());
                user.setLastName(req.lastName());
                user.setEmail(req.email());
                user.setPassword(passwordEncoder.encode(request.password()));
                return userMapper.toCreateResponse(userRepository.save(user));
            }).orElseThrow(() -> new ResourceAlreadyExistsException("Oops!" + request.email() + " already exists"));
    }

    @Override
    public UserUpdateResponse updateUser(Long userId, UserUpdateRequest request) {
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(request.firstName());
            user.setLastName(request.lastName());
            return userMapper.toUpdateResponse(userRepository.save(user));
        }).orElseThrow(() -> new ResourceNotFoundException(userId));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
            throw new ResourceNotFoundException(userId);
        });
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }
}
