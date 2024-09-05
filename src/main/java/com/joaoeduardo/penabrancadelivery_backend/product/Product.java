package com.joaoeduardo.penabrancadelivery_backend.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    private String description;

    private BigDecimal price;

    private String image;

    public Product(ProductCreateDTO dto) {
        this.id = UUID.randomUUID();

        this.title = dto.title();
        this.description = dto.description();
        this.price = dto.price().setScale(2, RoundingMode.HALF_UP);
        this.image = dto.image();
    }

    void updateData(ProductUpdateDTO dto){

        if(!dto.title().isBlank()) this.title = dto.title();
        if(!dto.description().isBlank()) this.description = dto.description();
        if(dto.price()!=null) this.price = dto.price();
        if(!dto.image().isBlank()) this.image = dto.image();

    }

}


















