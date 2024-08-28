package com.joaoeduardo.penabrancadelivery_backend.user;

import java.util.UUID;

public record UserResponse(UUID id, String email, String name, String password, String role) {
    UserResponse(User user){
        this(user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getRole());
    }
}
