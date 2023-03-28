package com.eurder.eurder.api.item;

import com.eurder.eurder.api.item.dto.CreateItemDto;
import com.eurder.eurder.api.item.dto.ItemDto;
import com.eurder.eurder.service.Item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @RequestMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getAllItems(){
        return itemService.getAllItems();
    }
    @PostMapping(value = "register", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestBody CreateItemDto createItemDto){
        return itemService.createItem(createItemDto);
    }
}
