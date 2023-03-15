package jpamarket.jpashopV2.service;

import jpamarket.jpashopV2.domain.Address;
import jpamarket.jpashopV2.domain.Member;
import jpamarket.jpashopV2.domain.Order;
import jpamarket.jpashopV2.domain.items.Book;
import jpamarket.jpashopV2.domain.items.Movie;
import jpamarket.jpashopV2.domain.status.DeliveryStatus;
import jpamarket.jpashopV2.domain.status.OrderStatus;
import jpamarket.jpashopV2.repository.ItemRepository;
import jpamarket.jpashopV2.repository.MemberRepository;
import jpamarket.jpashopV2.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext EntityManager em;
    @Autowired OrderRepository orderRepository;
    @Autowired OrderService orderService;
    @Autowired MemberRepository memberRepository;
    @Autowired ItemRepository itemRepository;

    @Test
    public void createOrder() {
        // Member 정보, 배송정보, Item 생성
        Member member = createMember();
        em.persist(member);
        Address address = new Address("Seoul");
        Movie newMovie = createMovie("movie1", "choi", 1000, 15000);
        Book newBook = createBook("book2", "bin", "150", 100, 10000);
        em.persist(newMovie);
        em.persist(newBook);

        Map<Long, Long> items = new HashMap<>();
        items.put(newMovie.getId(), 200L);
        items.put(newBook.getId(), 100L);
        Long orderId = orderService.order(member.getId(), items, address);

        // 주문생성 기능 check
        Order findOrder = orderRepository.findById(orderId).orElseThrow(IllegalAccessError::new);
        assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.ORDERED);
        assertThat(findOrder.getOrderItems().size()).isEqualTo(2);
        assertThat(findOrder.getDelivery().getDeliveryStatus()).isEqualTo(DeliveryStatus.ON);
    }

    @Test
    public void notEnoughStockOrder() {
        // Member 정보, 배송정보, Item 생성
        Member member = createMember();
        em.persist(member);
        Address address = new Address("Seoul");
        Movie newMovie = createMovie("movie1", "choi", 1000, 15000);
        Book newBook = createBook("book2", "bin", "150", 100, 10000);
        em.persist(newMovie);
        em.persist(newBook);

        Map<Long, Long> items = new HashMap<>();
        items.put(newMovie.getId(), 200L);
        items.put(newBook.getId(), 300L);

        // 재고 부족 Exception check
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            orderService.order(member.getId(), items, address);;
        });
        assertEquals("재고가 부족합니다.", exception.getMessage());


    }

    @Test
    public void cancelOrder() {
        // 주문 생성
        Member member = createMember();
        em.persist(member);
        Address address = new Address("Seoul");
        Movie newMovie = createMovie("movie1", "choi", 1000, 15000);
        Book newBook = createBook("book2", "bin", "150", 100, 10000);
        em.persist(newMovie);
        em.persist(newBook);

        Map<Long, Long> items = new HashMap<>();
        items.put(newMovie.getId(), 200L);
        items.put(newBook.getId(), 100L);
        Long orderId = orderService.order(member.getId(), items, address);

        // 주문 취소 확인
        orderService.cancelOrder(orderId);
        Order findOrderById = orderRepository.findById(orderId).orElseThrow(IllegalAccessError::new);
        assertThat(findOrderById.getOrderStatus()).isEqualTo(OrderStatus.CANCELED);

    }


    private Movie createMovie(String title, String director, int amount, int price) {
        Movie movie = new Movie();
        movie.setName(title);
        movie.setDirector(director);
        movie.setAmount(amount);
        movie.setPrice(price);

        return movie;
    }

    private Book createBook(String title, String author, String isbn, int amount, int price) {
        Book book = new Book();
        book.setName(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setAmount(amount);
        book.setPrice(price);

        return book;
    }

    public Member createMember() {
        Member member = new Member();
        member.setName("bin");
        return member;
    }


}