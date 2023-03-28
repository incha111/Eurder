package com.eurder.eurder.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {
    private final List<Item> itemList;

    public ItemRepository() {
        itemList = new ArrayList<>();
    }
    public List<Item> getAllItems(){
        return itemList.stream()
                .collect(Collectors.toList());
    }

    public Item save(Item item){
        itemList.add(item);
        return item;
    }

}
