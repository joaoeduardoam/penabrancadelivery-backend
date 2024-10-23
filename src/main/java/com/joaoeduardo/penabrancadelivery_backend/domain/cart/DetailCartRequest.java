package com.joaoeduardo.penabrancadelivery_backend.domain.cart;

import lombok.Builder;

import java.util.List;

@Builder
public record DetailCartRequest(List<DetailCartItemRequest> items) {
}
