package com.eurder.eurder.domain.item;

import com.eurder.eurder.domain.customer.Customer;
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
        // First sorting on stockAmount
        Comparator<Item> compareByStockAmount = Comparator
                .comparingInt(i -> i.getStockAmount());
        // Then sorting on urgency indicator
        Comparator<Item> compareByUrgencyIndicator = compareByStockAmount
                .thenComparingInt(i -> i.getUrgencyIndicator().getUrgencyLevel());

        return itemList.stream()
                .sorted(compareByStockAmount)
                .collect(Collectors.toList());
    }
    public Item getItemById(int id) {
        return itemList.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item not found for id " + id));
    }

    public Item save(Item item){
        if(!checkDuplicateInDatabase(item)){
            itemList.add(item);
        } else {
            throw new ResponseStatusException(HttpStatus.FOUND,"This item is already present in the database.");
        }
        return item;
    }

    public Item updateItem(Item item){
        return itemList.stream()
                .filter(i -> i.getId() == item.getId())
                .map(i -> itemList.set(itemList.indexOf(item),item))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No item found to update for id " + item.getId()));
    }
    private boolean checkDuplicateInDatabase(Item item){
        return itemList.stream()
                .anyMatch(i -> i.getName().equals(item.getName()));
    }
}
