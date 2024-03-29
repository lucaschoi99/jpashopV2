package jpamarket.jpashopV2.domain;

import jpamarket.jpashopV2.domain.status.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    private DeliveryStatus deliveryStatus;

    // Constructor
    protected Delivery() {
    }

    public Delivery(Address address) {
        this.address = address;
    }

}
