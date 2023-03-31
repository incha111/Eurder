package com.eurder.eurder.domain.item;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {
    private final List<Item> itemList;

    public ItemRepository() {
        itemList = new ArrayList<>();
    }
    public List<Item> getAllItems(){
        Comparator<Item> compareByUrgencyIndicatorAndByStockAmount = Comparator
                .comparing(Item::getUrgencyIndicator)
                .thenComparing(Item::getStockAmount);
        return itemList.stream()
                .sorted(compareByUrgencyIndicatorAndByStockAmount)
                .collect(Collectors.toList());
                /*.sorted((i1,i2) -> {
                    if(i1.getUrgencyIndicator().getUrgencyLevel() > i2.getUrgencyIndicator().getUrgencyLevel()) return 1;
                    if(i1.getUrgencyIndicator().getUrgencyLevel() < i2.getUrgencyIndicator().getUrgencyLevel()) return -1;
                    return 0;
                })
                .sorted(Comparator.comparing(i -> (i.getUrgencyIndicator().getUrgencyLevel())))
                .collect(Collectors.toList());*/
    }
    public Item getItemById(int id) {
        return itemList.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item not found for id " + id));
    }

    public Item save(Item item){
        itemList.add(item);
        return item;
    }

    public Item updateItem(Item item){
        return itemList.stream()
                .filter(i -> i.getId() == item.getId())
                .map(i -> itemList.set(itemList.indexOf(item),item))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No item found to update for id " + item.getId()));
    }
}
