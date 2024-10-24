package com.joaoeduardo.penabrancadelivery_backend.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllByIdIn(List<UUID> idList);

}
