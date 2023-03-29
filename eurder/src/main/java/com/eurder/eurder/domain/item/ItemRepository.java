package com.eurder.eurder.domain.item;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {
    private final List<Item> itemList;

    public ItemRepository() {

        itemList = new ArrayList<>();
        itemList.add(new Item("Ping pong rackets (pair)","Set of 2 rackets to play ping pong.",10.0,7));
        itemList.add(new Item("Ping pong ball","Package of 6 balls",2.0,10));

    }
    public List<Item> getAllItems(){
        return itemList.stream()
                .collect(Collectors.toList());
    }

    public Item save(Item item){
        itemList.add(item);
        return item;
    }


    public Item getItemById(int id) {
        return itemList.stream()
                .filter(i -> i.getItemId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item not found for id " + id));
    }
    public Item updateItem(Item item){
        return itemList.stream()
                .filter(i -> i.getItemId() == item.getItemId())
                .map(i -> itemList.set(itemList.indexOf(item),item))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No item found to update for id " + item.getItemId()));
    }
}
