package com.eurder.eurder.api.item;

import com.eurder.eurder.api.item.dto.CreateItemDto;
import com.eurder.eurder.api.item.dto.ItemDto;
import com.eurder.eurder.api.item.dto.UpdateItemDto;
import com.eurder.eurder.service.Item.ItemService;
import com.eurder.eurder.service.security.Feature;
import com.eurder.eurder.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;
    private final SecurityService securityService;

    @Autowired
    public ItemController(ItemService itemService, SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getAllItems(@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.ITEMS_OVERVIEW);
        return itemService.getAllItems();
    }
    @PostMapping(value = "register", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestBody CreateItemDto createItemDto,@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.ADD_ITEM);
        return itemService.createItem(createItemDto);
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ItemDto getItemById(@PathVariable int id){
        return itemService.getItemById(id);
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ItemDto updateItem(@PathVariable int id, @RequestBody UpdateItemDto updateItemDto,@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.UPDATE_ITEM);
        return itemService.updateItem(id,updateItemDto);
    }
}
