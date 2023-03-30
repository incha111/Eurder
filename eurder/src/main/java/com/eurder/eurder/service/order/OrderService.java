package com.eurder.eurder.service.order;

import com.eurder.eurder.api.item.dto.CreateItemGroupDto;
import com.eurder.eurder.api.order.CreateOrderDto;
import com.eurder.eurder.api.order.OrderDto;
import com.eurder.eurder.domain.customer.CustomerRepository;
import com.eurder.eurder.domain.item.Item;
import com.eurder.eurder.domain.item.ItemGroup;
import com.eurder.eurder.domain.item.ItemRepository;
import com.eurder.eurder.domain.order.Order;
import com.eurder.eurder.domain.order.OrderRepository;
import com.eurder.eurder.service.Item.ItemService;
import com.eurder.eurder.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public static final int SHIPPINGDAYS_WHEN_ITEM_IS_IN_STOCK = 1;
    public static final int SHIPPINGDAYS_WHEN_ITEM_IS_NOT_IN_STOCK = 7;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, CustomerService customerService, ItemService itemService, ItemRepository itemRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerService = customerService;
        this.itemService = itemService;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
    }

    public List<OrderDto> getAllOrders(){
        return orderMapper.toDto(orderRepository.getAllOrders());
    }
    public OrderDto getOrderById(int orderId){
        return orderMapper.toDto(orderRepository.getOrderById(orderId));
    }

    public OrderDto createOrder(CreateOrderDto createOrderDto){
        List<ItemGroup> itemGroupList = createOrderItemGroupList(createOrderDto);

        Order order = new Order(
                createOrderDto.getOrderDate(),
                createOrderDto.getCustomerId(),
                itemGroupList,
                calculateTotalPrice(itemGroupList));

        return orderMapper.toDto(orderRepository.save(order));
    }
    private List<ItemGroup> createOrderItemGroupList(CreateOrderDto createOrderDto){
        List<ItemGroup> itemGroupList = new ArrayList<>();
        List<CreateItemGroupDto> createItemGroupDtoList = createOrderDto.getCreateItemGroupDto();

        for(CreateItemGroupDto createItemGroupDto : createItemGroupDtoList){
            Item item = itemRepository.getItemById(createItemGroupDto.getItemId());
            //new itemGroup
            ItemGroup orderItemGroup = new ItemGroup(
                    item,
                    createItemGroupDto.getOrderedItemAmount(),
                    calculateShippingDate(item,createItemGroupDto,createOrderDto.getOrderDate()),
                    calculateGroupPrice(item,createItemGroupDto)
            );
            itemGroupList.add(orderItemGroup);
            updateItemStockAmount(item,createItemGroupDto);
        }

        return itemGroupList;
    }

    private LocalDate calculateShippingDate(Item item, CreateItemGroupDto createItemGroupDto,LocalDate orderDate){
        if(isItemInStock(item,createItemGroupDto)){
            return orderDate.plusDays(SHIPPINGDAYS_WHEN_ITEM_IS_IN_STOCK);
        }
        return orderDate.plusDays(SHIPPINGDAYS_WHEN_ITEM_IS_NOT_IN_STOCK);
    }
    private boolean isItemInStock(Item item,CreateItemGroupDto createItemGroupDto){
        if(item.getStockAmount() >= createItemGroupDto.getOrderedItemAmount()){
            return true;
        }
        return false;
    }
    private double calculateGroupPrice(Item item, CreateItemGroupDto createItemGroupDto){
        return item.getPrice() * createItemGroupDto.getOrderedItemAmount();
    }

    private double calculateTotalPrice(List<ItemGroup> newItemGroupList){
        return newItemGroupList.stream()
                .map(i -> i.getGroupPrice())
                .reduce(0.0, (a,b) -> a + b);
    }


    private void updateItemStockAmount(Item item,CreateItemGroupDto createItemGroupDto){
        if(isItemInStock(item,createItemGroupDto)){
            item.changeStockAmount(item.getStockAmount() - createItemGroupDto.getOrderedItemAmount());
            itemRepository.updateItem(item);
        }
    }
}
