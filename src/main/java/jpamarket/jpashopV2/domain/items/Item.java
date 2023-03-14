package jpamarket.jpashopV2.domain.items;

import jpamarket.jpashopV2.domain.ItemCategory;
import jpamarket.jpashopV2.domain.exceptions.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int amount;

    @OneToMany(mappedBy = "item")
    private List<ItemCategory> categories = new ArrayList<>();

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
