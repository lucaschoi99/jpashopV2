package jpamarket.jpashopV2.domain;

import jpamarket.jpashopV2.domain.Category;
import jpamarket.jpashopV2.domain.exceptions.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter @Setter
@Builder
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    // 공통 속성
    private Category category;
    private String name;
    private int price;
    private int amount;

    // Album
    private String artist;
    // Book
    private String author;
    private String isbn;
    // Movie
    private String director;
    private String actor;

    // 재고 관리 메소드
    public void removeStock(int count) {
        int result = this.amount - count;
        if (result < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }
        this.amount = result;
    }

    public void addStock(int count) {
        this.amount += count;
    }
}
