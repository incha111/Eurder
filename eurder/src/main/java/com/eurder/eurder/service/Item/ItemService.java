package com.eurder.eurder.service.Item;

import com.eurder.eurder.api.item.dto.CreateItemDto;
import com.eurder.eurder.api.item.dto.ItemDto;
import com.eurder.eurder.api.item.dto.UpdateItemDto;
import com.eurder.eurder.domain.item.Item;
import com.eurder.eurder.domain.item.ItemRepository;
import com.eurder.eurder.domain.item.ItemRepositoryJpa;
import com.eurder.eurder.service.exceptions.ItemNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDto> getAllItems() {
        return itemMapper.toDto(itemRepository.findAll());
    }

    public ItemDto createItem(CreateItemDto createItemDto) {
        return itemMapper.toDto(itemRepository.save(new Item(
                createItemDto.getName(),
                createItemDto.getDescription(),
                createItemDto.getPrice(),
                createItemDto.getStockAmount()
        )));
    }

    public ItemDto getItemById(Integer itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(()-> new ItemNotFoundException("Item not found for id " +itemId));
        return itemMapper.toDto(item);
    }
    public ItemDto updateItem(int id, UpdateItemDto updateItemDto){
        Item item = itemRepository.findById(id).get();
        item.changeName(updateItemDto.getName());
        item.changeDescription(updateItemDto.getDescription());
        item.changePrice(updateItemDto.getPrice());
        item.changeStockAmount(updateItemDto.getStockAmount());

        return itemMapper.toDto(item);
    }
}
