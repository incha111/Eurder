package com.eurder.eurder.service.Item;

import com.eurder.eurder.api.item.dto.CreateItemDto;
import com.eurder.eurder.api.item.dto.ItemDto;
import com.eurder.eurder.domain.item.Item;
import com.eurder.eurder.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDto> getAllItems() {
        return itemMapper.toDto(itemRepository.getAllItems());
    }

    public ItemDto createItem(CreateItemDto createItemDto) {
        return itemMapper.toDto(itemRepository.save(new Item(
                createItemDto.getName(),
                createItemDto.getDescription(),
                createItemDto.getPrice(),
                createItemDto.getStockAmount()
        )));
    }
}
