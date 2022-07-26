package jpamarket.jpashopV2.domain;

import jpamarket.jpashopV2.domain.items.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "orderitem_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item items;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order orders;

    private int orderPrice;
    private int count;

}
