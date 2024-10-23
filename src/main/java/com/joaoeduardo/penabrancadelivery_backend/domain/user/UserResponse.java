package com.joaoeduardo.penabrancadelivery_backend.domain.user;

import java.util.UUID;

public record UserResponse(UUID id, String email, String name, String verificationCode, UserRole role) {
    UserResponse(User user){
        this(user.getId(), user.getEmail(), user.getName(), user.getVerificationCode(), user.getRole());
    }
}
