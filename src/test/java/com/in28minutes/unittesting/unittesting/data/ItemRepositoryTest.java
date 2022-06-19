package com.in28minutes.unittesting.unittesting.data;

import com.in28minutes.unittesting.unittesting.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @DataJpaTest
 * h2(in memory db) DB 테스트 가능하다록 해줌
 * https://webcoding-start.tistory.com/20
 */
@DataJpaTest
//@RunWith(SpringRunner.class)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testFindAll() {
        List<Item> items = itemRepository.findAll();
        assertEquals(4, items.size());
    }
}