package jpamarket.jpashopV2.service;

import jpamarket.jpashopV2.domain.*;
import jpamarket.jpashopV2.domain.items.Item;
import jpamarket.jpashopV2.domain.status.DeliveryStatus;
import jpamarket.jpashopV2.repository.ItemRepository;
import jpamarket.jpashopV2.repository.MemberRepository;
import jpamarket.jpashopV2.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문
    public Long order(Long memberId, Map<Long, Long> infos, Address address) {
        // 주문자 정보
        Member findMember = memberRepository.findMember(memberId);

        // 배송 정보
        Delivery delivery = new Delivery(address);
        delivery.setDeliveryStatus(DeliveryStatus.ON);

        // OrderItem 생성
        List<OrderItem> orderItemList = new ArrayList<>();
        Set<Long> keySets = infos.keySet();
        for (Long itemId : keySets) {
            Long count = infos.get(itemId);
            Item findItem = itemRepository.findItem(itemId);

            OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count.intValue());
            orderItemList.add(orderItem);
        }

        // Order 생성
        Order order = Order.createOrder(findMember, delivery, orderItemList);

        // Order 저장
        return orderRepository.save(order);
    }


    // 주문 취소
    public void cancelOrder(Long orderId) {
        Order findOrder = orderRepository.findOrderById(orderId);
        findOrder.cancel();
    }


}
