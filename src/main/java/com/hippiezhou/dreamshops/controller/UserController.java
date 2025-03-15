package com.hippiezhou.dreamshops.controller;

import com.hippiezhou.dreamshops.dto.user.*;
import com.hippiezhou.dreamshops.exception.ResourceAlreadyExistsException;
import com.hippiezhou.dreamshops.exception.ResourceNotFoundException;
import com.hippiezhou.dreamshops.model.User;
import com.hippiezhou.dreamshops.response.ApiResponse;
import com.hippiezhou.dreamshops.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID")
    @ApiResponses(
        value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "User found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "User not found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponse.class))
            )
        }
    )
    public ResponseEntity<ApiResponse> getUserId(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            UserDto userDto = userService.convertToDto(user);
            return ResponseEntity.ok(new ApiResponse("User found",
                new UserGetResponse(
                    userDto.getId(),
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getEmail())));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("")
    @Operation(summary = "Create user")
    @ApiResponses(
        value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "User created",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "409",
                description = "User already exists",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponse.class))
            )
        }
    )
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserCreateRequest user) {
        try {
            User newUser = userService.createUser(user);
            UserDto userDto = userService.convertToDto(newUser);
            return ResponseEntity.ok(new ApiResponse("User created",
                new UserCreateResponse(
                    userDto.getId(),
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getEmail())));
        } catch (ResourceAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user by ID")
    @ApiResponses(
        value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "User updated",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "User not found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponse.class))
            )
        }
    )
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequest user) {
        try {
            User updatedUser = userService.updateUser(userId, user);
            UserDto userDto = userService.convertToDto(updatedUser);
            return ResponseEntity.ok(new ApiResponse("User updated",
                new UserUpdateResponse(
                    userDto.getFirstName(),
                    userDto.getLastName())));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user by ID")
    @ApiResponses(
        value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "User deleted",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "User not found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponse.class))
            )
        }
    )
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("User deleted", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
