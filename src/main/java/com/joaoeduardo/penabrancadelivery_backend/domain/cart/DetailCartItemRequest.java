package com.joaoeduardo.penabrancadelivery_backend.domain.cart;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DetailCartItemRequest(UUID productId, long quantity) {
}
