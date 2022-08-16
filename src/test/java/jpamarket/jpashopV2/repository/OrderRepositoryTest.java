package jpamarket.jpashopV2.repository;

import jpamarket.jpashopV2.domain.*;
import jpamarket.jpashopV2.domain.items.Book;
import jpamarket.jpashopV2.domain.items.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired OrderRepository orderRepository;
    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    public void saveAndFind() {
//        Member member = new Member("bin", new Address("busan"));
//        memberRepository.save(member);
//        Book book = createBook("choi's book", "choi", "1234", 10, 20000);
//        Movie movie = createMovie("choi's movie", "choi", 5, 40000);
//        OrderItem orderBook = OrderItem.createOrderItem(book, 20000, 2);
//        OrderItem orderMovie = OrderItem.createOrderItem(movie, 40000, 1);
//        Order order = Order.createOrder(member, new Delivery(new Address("busan")), orderBook, orderMovie);
//        Long orderId = orderRepository.save(order);
//
//        Order findOrder = orderRepository.findOrderById(orderId);
//
//        assertThat(findOrder.getId()).isEqualTo(orderId);
//        assertThat(findOrder.getMember()).isEqualTo(member);

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


}