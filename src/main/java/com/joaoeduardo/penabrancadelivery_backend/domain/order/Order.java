package com.joaoeduardo.penabrancadelivery_backend.domain.order;

import com.joaoeduardo.penabrancadelivery_backend.domain.user.User;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

//@Entity
//@Table(name = "orders")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private OrderStatus status;

    private User user;

    private String stripeId;

    private List<OrderedProduct> orderedProducts;


    public static Order create(User connectedUser, List<OrderedProduct> orderedProducts, String stripeId){

        return Order.builder()
                .id(UUID.randomUUID())
                .status(OrderStatus.PENDING)
                .user(connectedUser)
                .stripeId(stripeId)
                .orderedProducts(orderedProducts)
                .build();
    }

    public void validatePayment(){
        this.status = OrderStatus.PAID;
    }

}
