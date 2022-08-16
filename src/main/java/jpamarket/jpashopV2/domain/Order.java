package jpamarket.jpashopV2.domain;

import jpamarket.jpashopV2.domain.status.DeliveryStatus;
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

    //== 비즈니스 로직 ==//
    // 주문 생성
    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems) {
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

    // 주문 취소
    public void cancel() {
        if (delivery.getDeliveryStatus() == DeliveryStatus.OVER) {
            throw new IllegalStateException("이미 배송완료된 상품입니다.");
        }
        this.setOrderStatus(OrderStatus.CANCELED);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }


    // Order-Member 연관 메소드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    // Order-Delivery 연관 메소드
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // Order-OrderItem 연관 메소드
    public void setOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }





}
