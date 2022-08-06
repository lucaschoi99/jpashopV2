package jpamarket.jpashopV2.repository;

import jpamarket.jpashopV2.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    // Order 저장
    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    // Order 단건조회
    public Order findOrderById(Long id) {
        Order order = em.find(Order.class, id);
        return order;
    }

    // Order 전체조회
    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

}
