package jpamarket.jpashopV2.domain.items;

import jpamarket.jpashopV2.domain.ItemCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

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

}
