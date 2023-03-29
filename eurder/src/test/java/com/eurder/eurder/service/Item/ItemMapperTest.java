package com.eurder.eurder.service.Item;

import com.eurder.eurder.api.item.dto.ItemDto;
import com.eurder.eurder.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

    ItemMapper itemMapper;

    @BeforeEach
    void setUpItemMapperTest() {
        itemMapper = new ItemMapper();
    }

    @Test
    void toDto_givingAnItem_thenReturnsAnItemDto() {
        //given
        Item item = new Item("Ping pong net","A net to install on a ping pong table",15.0,2);

        //when
        ItemDto itemDto = itemMapper.toDto(item);

        //then
        Assertions.assertThat(itemDto).isEqualTo(itemMapper.toDto(item));
    }

    @Test
    void toDto_givingAListOfItems_thenReturnsAListOfItemDto() {
        //given
        Item item1 = new Item("Ping pong rackets (pair)","Set of 2 rackets to play ping pong.",10.0,7);
        Item item2 = new Item("Ping pong ball","Package of 6 balls",2.0,10);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);

        //when
        List<ItemDto> itemDtoList = itemMapper.toDto(itemList);

        //then
        Assertions.assertThat(itemDtoList).isEqualTo(itemMapper.toDto(itemList));

    }


}