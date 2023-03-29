package com.eurder.eurder.service.Item;

import com.eurder.eurder.api.item.dto.ItemDto;
import com.eurder.eurder.domain.item.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public ItemDto toDto(Item item){
        return new ItemDto(
                item.getItemId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getStockAmount()
        );
    }

    public List<ItemDto> toDto(List<Item> itemList){
        return itemList.stream()
                .map(i -> toDto(i))
                .collect(Collectors.toList());
    }
}
