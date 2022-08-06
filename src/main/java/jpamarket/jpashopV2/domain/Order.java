package jpamarket.jpashopV2.domain;

import jpamarket.jpashopV2.domain.status.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime dateTime;
    private OrderStatus orderStatus;

    // Constructor - 직접 생성 금지
    protected Order() {
    }

    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.setOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDERED);
        order.setDateTime(LocalDateTime.now());

        return order;
    }

    // Order-Member 연관 메소드
    public void setMember(Member member) {
        this.setMember(member);
        member.getOrders().add(this);
    }

    // Order-Delivery 연관 메소드
    public void setDelivery(Delivery delivery) {
        this.setDelivery(delivery);
        delivery.setOrder(this);
    }

    // Order-OrderItem 연관 메소드
    public void setOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }





}
