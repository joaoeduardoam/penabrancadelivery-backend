package com.joaoeduardo.penabrancadelivery_backend.user;

public record UserCreateDTO(
        String email,
        String name,
        String password,
        UserRole role) {

}
