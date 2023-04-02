package com.eurder.eurder.service.Item;

import com.eurder.eurder.api.item.dto.ItemDto;
import com.eurder.eurder.api.item.dto.UpdateItemDto;
import com.eurder.eurder.domain.item.Item;
import com.eurder.eurder.domain.item.ItemRepository;
import com.eurder.eurder.domain.item.UrgencyIndicator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class ItemServiceTest {
    ItemRepository itemRepositoryMock;
    ItemMapper itemMapperMock;
    ItemService itemService;

    @BeforeEach
    void setUp() {
        itemRepositoryMock = Mockito.mock(ItemRepository.class);
        itemMapperMock = Mockito.mock(ItemMapper.class);
        itemService = new ItemService(itemRepositoryMock,itemMapperMock);
    }

    @Test
    void getAllItems_verifyMethodGetAllItemsIsCalledOnItemService() {
        //given
        //when
        itemService.getAllItems();

        //then
        Mockito.verify(itemRepositoryMock).getAllItems();
    }
    @Test
    void getAllItems_givenAListOfItems_returnAListOfItemDto() {
        //given
        ItemMapper itemMapper = new ItemMapper();
        Item item1 = new Item("Ping pong rackets (pair)","Set of 2 rackets to play ping pong.",10.0,7);
        Item item2 = new Item("Ping pong ball","Package of 6 balls",2.0,10);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        Mockito.when(itemService.getAllItems())
                .thenReturn(itemMapper.toDto(itemList));

        //when
        List<ItemDto> itemDtoList = itemService.getAllItems();

        //then
        Assertions.assertThat(itemDtoList).isEqualTo(itemService.getAllItems());
    }

    @Test
    void getItemById_givenAnItemId_thenReturnsThatItem() {
        //given
        int id = 1;
        ItemMapper itemMapper = new ItemMapper();
        Item item1 = new Item("Ping pong rackets (pair)","Set of 2 rackets to play ping pong.",10.0,7);
        Mockito.when(itemService.getItemById(id))
                .thenReturn(itemMapper.toDto(item1));
        //when
        ItemDto itemDto = itemService.getItemById(id);

        //then
        Assertions.assertThat(itemDto).isEqualTo(itemService.getItemById(id));
    }
    @Test
    void updateItem_givenNewDataToAnItem_thenReturnsThatItemWithNewData() {
        //given
        int id = 1;
        ItemMapper itemMapper = new ItemMapper();
        Item item1 = new Item("Ping pong rackets (pair)","Set of 2 rackets to play ping pong.",10.0,7);
        UpdateItemDto updateItemDto = new UpdateItemDto("Ping pong rackets (pair)", "Set of 2 rackets to play ping pong.", 8.0,5);
        Mockito.when(itemRepositoryMock.getItemById(id))
                        .thenReturn(item1);
        Mockito.when(itemService.updateItem(id, updateItemDto))
                .thenReturn(itemMapper.toDto(item1));
        Mockito.when((itemService.getItemById(id)))
                .thenReturn(itemMapper.toDto(item1));
        //when
        ItemDto itemDto = itemService.updateItem(id,updateItemDto);

        //then
        Assertions.assertThat(itemDto.getPrice()).isEqualTo(itemService.getItemById(id).getPrice());
        Assertions.assertThat(itemDto.getStockAmount()).isEqualTo(itemService.getItemById(id).getStockAmount());
    }

    @Test
    void updateItem_verifyMethodUpdateItemIsCalledOnItemService() {
        //given
        ItemMapper itemMapper = new ItemMapper();
        Item item1 = new Item("Ping pong rackets (pair)","Set of 2 rackets to play ping pong.",10.0,7);
        UpdateItemDto updateItemDto1 = new UpdateItemDto("Ping pong rackets (pair)","Set of 2 rackets to play ping pong.",15.0,7);
        Mockito.when(itemRepositoryMock.getItemById(item1.getId()))
                .thenReturn(item1);
        //when
        itemService.updateItem(item1.getId(),updateItemDto1);

        //then
        Mockito.verify(itemRepositoryMock).updateItem(item1);
    }


    // test for save!!!!!
}