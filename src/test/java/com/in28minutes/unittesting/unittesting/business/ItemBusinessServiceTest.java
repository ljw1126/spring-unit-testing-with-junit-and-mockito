package com.in28minutes.unittesting.unittesting.business;

import com.in28minutes.unittesting.unittesting.data.ItemRepository;
import com.in28minutes.unittesting.unittesting.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


//@RunWith(MockitoJUnitRunner.class) 요거 하니 실행 안됨
@ExtendWith(MockitoExtension.class)
class ItemBusinessServiceTest {

    @InjectMocks
    private ItemBusinessService itemBusinessService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void retrieveAllItems_basic() {
        when(itemRepository.findAll())
                .thenReturn(Arrays.asList(new Item(10001, "Item1", 10, 20), new Item(10002, "Item2", 5, 10)));

        List<Item> items = itemBusinessService.retrieveAllItems();

        assertEquals(200, items.get(0).getValue());
        assertEquals(50, items.get(1).getValue());
    }

}