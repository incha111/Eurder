package com.eurder.eurder.service.order;

import com.eurder.eurder.api.item.dto.CreateItemGroupDto;
import com.eurder.eurder.api.order.dto.CreateOrderDto;
import com.eurder.eurder.api.order.dto.OrderDto;
import com.eurder.eurder.api.order.dto.ViewOrderReportDto;
import com.eurder.eurder.domain.item.Item;
import com.eurder.eurder.domain.item.ItemGroup;
import com.eurder.eurder.domain.item.ItemRepository;
import com.eurder.eurder.domain.item.ItemRepositoryJpa;
import com.eurder.eurder.domain.order.Order;
import com.eurder.eurder.domain.order.OrderRepository;
import com.eurder.eurder.service.Item.ItemMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {
    public static final int SHIPPINGDAYS_WHEN_ITEM_IS_IN_STOCK = 1;
    public static final int SHIPPINGDAYS_WHEN_ITEM_IS_NOT_IN_STOCK = 7;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ItemMapper itemMapper, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public List<OrderDto> getAllOrders(){
        return orderMapper.toDto(orderRepository.findAll());
    }
    public OrderDto getOrderById(int orderId){
        return orderMapper.toDto(orderRepository.findById(orderId).get());
    }

    
    public OrderDto reOrder(int orderId,int customerId){
        Order order = orderRepository.findOrderByOrderIdAndCustomerId(orderId,customerId);

        List<CreateItemGroupDto> createItemGroupDtoList = retrieveCreateItemGroupDtoListFromOrder(order);
        
        return createOrder(new CreateOrderDto(
                createItemGroupDtoList,
                customerId,
                LocalDate.now()
        ));
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
            Item item = itemRepository.findById(createItemGroupDto.getId()).get();
            Item itemCopy = new Item(item.getId(),item.getName(),item.getDescription(),item.getPrice(),item.getStockAmount());
            //new itemGroup
            ItemGroup orderItemGroup = new ItemGroup(
                    itemCopy,
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
        } else {
            item.changeStockAmount(0);
        }
        item.changeUrgencyIndicator();
        //itemRepository.updateItem(item);

    }
    private List<CreateItemGroupDto> retrieveCreateItemGroupDtoListFromOrder(Order order){
        List<CreateItemGroupDto> createItemGroupDtoList = new ArrayList<>();
        for(ItemGroup itemGroup: order.getItemGroupList()){
            CreateItemGroupDto createItemGroupDto = new CreateItemGroupDto(
                    itemGroup.getItem().getId(),
                    itemGroup.getOrderedItemAmount()
            );
            createItemGroupDtoList.add(createItemGroupDto);
        }
        return createItemGroupDtoList;
    }
    public ViewOrderReportDto getCustomerOrders(int customerId){
        return orderMapper.toViewReportDto(orderMapper.toViewDto((orderRepository.findOrdersByCustomerId(customerId))));
    }
}
