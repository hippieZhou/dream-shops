package com.hippiezhou.dreamshops.service.impl;

import com.hippiezhou.dreamshops.dto.UserDto;
import com.hippiezhou.dreamshops.exception.ResourceAlreadyExistsException;
import com.hippiezhou.dreamshops.exception.ResourceNotFoundException;
import com.hippiezhou.dreamshops.model.User;
import com.hippiezhou.dreamshops.repository.UserRepository;
import com.hippiezhou.dreamshops.request.UserCreateRequest;
import com.hippiezhou.dreamshops.request.UserUpdateRequest;
import com.hippiezhou.dreamshops.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException(userId));
    }

    @Override
    public User createUser(UserCreateRequest request) {
        return Optional.of(request)
            .filter(user -> !userRepository.existsByEmail(user.getEmail()))
            .map(req -> {
                User user = new User();
                user.setFirstName(req.getFirstName());
                user.setLastName(req.getLastName());
                user.setEmail(req.getEmail());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                return userRepository.save(user);
            }).orElseThrow(() -> new ResourceAlreadyExistsException("Oops!" + request.getEmail() + " already exists"));
    }

    @Override
    public User updateUser(Long userId, UserUpdateRequest request) {
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException(userId));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
            throw new ResourceNotFoundException(userId);
        });
    }

    @Override
    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }
}
