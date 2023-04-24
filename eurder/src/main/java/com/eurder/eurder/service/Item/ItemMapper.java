package com.eurder.eurder.service.Item;

import com.eurder.eurder.api.item.dto.ItemDto;
import com.eurder.eurder.api.item.dto.ViewItemGroupReport;
import com.eurder.eurder.api.order.dto.CreateOrderDto;
import com.eurder.eurder.domain.item.Item;
import com.eurder.eurder.domain.item.ItemGroup;
import com.eurder.eurder.domain.item.UrgencyIndicator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public ItemDto toDto(Item item){
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getStockAmount(),
                item.changeUrgencyIndicator()
        );
    }

    public List<ItemDto> toDto(List<Item> itemList){
        return itemList.stream()
                .map(i -> toDto(i))
                .collect(Collectors.toList());
    }


    public ViewItemGroupReport toViewDto(ItemGroup itemGroup){
        itemGroup.calculateGroupPrice();
        return new ViewItemGroupReport(
                itemGroup.getItem().getName(),
                itemGroup.getOrderedItemAmount(),
                itemGroup.getGroupPrice()
        );
    }
    public List<ViewItemGroupReport> toViewDto(List<ItemGroup> itemGroupList){
        return itemGroupList.stream()
                .map(i -> toViewDto(i))
                .collect(Collectors.toList());
    }

}
