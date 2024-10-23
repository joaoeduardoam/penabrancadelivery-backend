package com.joaoeduardo.penabrancadelivery_backend.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private String role;

    UserRole (String role){
        this.role=role;
    }

}
