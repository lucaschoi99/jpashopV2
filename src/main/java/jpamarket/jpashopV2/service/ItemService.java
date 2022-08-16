package jpamarket.jpashopV2.service;

import jpamarket.jpashopV2.domain.items.Item;
import jpamarket.jpashopV2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
ItemService -> ItemRepository 위임하는 서비스 계층
 */

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    // Item 저장
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 단건 조회
    public Item findItem(Long id) {
        return itemRepository.findItem(id);
    }

    // 전체 조회
    public List<Item> findAll() {
        return itemRepository.findAll();
    }


}
