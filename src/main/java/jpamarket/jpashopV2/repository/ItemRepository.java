package jpamarket.jpashopV2.repository;

import jpamarket.jpashopV2.domain.items.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 저장
    public Long save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        }
        else{
            Item findItem = em.find(Item.class, item.getId());
            updateItem(item, findItem); // Dirty checking 수정
        }
        return item.getId();
    }

    private void updateItem(Item item, Item findItem) {
        findItem.setName(item.getName());
        findItem.setPrice(item.getPrice());
        findItem.setAmount(item.getAmount());
    }

    // 단건 조회
    public Item findItem(Long id) {
        return em.find(Item.class, id);
    }

    // 모두 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
