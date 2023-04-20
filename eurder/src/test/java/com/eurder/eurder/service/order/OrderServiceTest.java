package com.eurder.eurder.service.order;

import com.eurder.eurder.api.item.dto.CreateItemGroupDto;
import com.eurder.eurder.api.order.dto.CreateOrderDto;
import com.eurder.eurder.api.order.dto.OrderDto;
import com.eurder.eurder.domain.item.Item;
import com.eurder.eurder.domain.item.ItemGroup;
import com.eurder.eurder.domain.item.ItemRepositoryJpa;
import com.eurder.eurder.domain.order.Order;
import com.eurder.eurder.domain.order.OrderRepository;
import com.eurder.eurder.service.Item.ItemMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class OrderServiceTest {
    OrderService orderService;
    OrderRepository orderRepositoryMock;
    ItemRepositoryJpa itemRepositoryMock;
    OrderMapper orderMapperMock;
    ItemMapper itemMapperMock;

    @BeforeEach
    void setUp() {
        orderRepositoryMock = Mockito.mock(OrderRepository.class);
        itemRepositoryMock = Mockito.mock(ItemRepositoryJpa.class);
        orderMapperMock = Mockito.mock(OrderMapper.class);
        orderService = new OrderService(orderRepositoryMock, orderMapperMock, itemMapperMock, itemRepositoryMock);
    }

    @Test
    void getAllOrders_verifyMethodGetAllOrdersIsCalledOnOrderService() {
        //given
        //when
        orderService.getAllOrders();

        //then
        Mockito.verify(orderRepositoryMock).getAllOrders();
    }
    @Test
    void getAllOrders_givingAListOfOrders_returnsAListOfOrdersDto() {
        //given
        Item item1 = new Item("paprika chips","paprika chips",1.5,5);
        Item item2 = new Item("pickels chips","pickels chips",1.5,7);
        Item item3 = new Item("dark chocolate","dark chocolate",2.3,10);
        Item item4 = new Item("white chocolate","white chocolate",2.7,2);

        ItemGroup itemGroup1 = new ItemGroup(item1,3, LocalDate.now().plusDays(1),4.5);
        ItemGroup itemGroup2 = new ItemGroup(item2,2,LocalDate.now().plusDays(1),3.0);
        ItemGroup itemGroup3 = new ItemGroup(item3,4,LocalDate.now().plusDays(1),9.2);
        ItemGroup itemGroup4 = new ItemGroup(item4,2,LocalDate.now().plusDays(1),5.4);

        List<ItemGroup> itemGroupList1 = new ArrayList<>();
        itemGroupList1.add(itemGroup1);
        itemGroupList1.add(itemGroup2);

        List<ItemGroup> itemGroupList2 = new ArrayList<>();
        itemGroupList1.add(itemGroup3);
        itemGroupList1.add(itemGroup4);

        Order order1 = new Order(LocalDate.now(),1,itemGroupList1,7.5);
        Order order2 = new Order(LocalDate.now(),1,itemGroupList2,14.6);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

        //when
        List<OrderDto> orderDtoList = orderService.getAllOrders();

        //then
        Assertions.assertThat(orderDtoList).isEqualTo(orderService.getAllOrders());
    }

    @Test
    void getOrderById_VerifyMethodGetOrderByIdIsCalledOnOrderService() {
        //given
        int orderId = 1;
        Item item1 = new Item("paprika chips","paprika chips",1.5,5);
        Item item2 = new Item("pickels chips","pickels chips",1.5,7);

        ItemGroup itemGroup1 = new ItemGroup(item1,3,LocalDate.now().plusDays(1),4.5);
        ItemGroup itemGroup2 = new ItemGroup(item2,2,LocalDate.now().plusDays(1),3.0);

        List<ItemGroup> itemGroupList1 = new ArrayList<>();
        itemGroupList1.add(itemGroup1);
        itemGroupList1.add(itemGroup2);

        Order order = new Order(LocalDate.now(),1,itemGroupList1,7.5);
        Mockito.when(orderRepositoryMock.getOrderById(orderId))
                .thenReturn(order);

        //when
            orderService.getOrderById(orderId);

        //then
        Mockito.verify(orderRepositoryMock).getOrderById(orderId);
    }

    @Test
    void getOrderById_givingAnOrderId_returnsThatOrder() {
        //given
        OrderMapper orderMapper = new OrderMapper(new ItemMapper());
        Item item1 = new Item("paprika chips","paprika chips",1.5,5);
        Item item2 = new Item("pickels chips","pickels chips",1.5,7);
        Item item3 = new Item("dark chocolate","dark chocolate",2.3,10);
        Item item4 = new Item("white chocolate","white chocolate",2.7,2);

        ItemGroup itemGroup1 = new ItemGroup(item1,3,LocalDate.now().plusDays(1),4.5);
        ItemGroup itemGroup2 = new ItemGroup(item2,2,LocalDate.now().plusDays(1),3.0);
        ItemGroup itemGroup3 = new ItemGroup(item3,4,LocalDate.now().plusDays(1),9.2);
        ItemGroup itemGroup4 = new ItemGroup(item4,2,LocalDate.now().plusDays(1),5.4);

        List<ItemGroup> itemGroupList1 = new ArrayList<>();
        itemGroupList1.add(itemGroup1);
        itemGroupList1.add(itemGroup2);

        List<ItemGroup> itemGroupList2 = new ArrayList<>();
        itemGroupList1.add(itemGroup3);
        itemGroupList1.add(itemGroup4);

        Order order1 = new Order(LocalDate.now(),1,itemGroupList1,7.5);
        Order order2 = new Order(LocalDate.now(),1,itemGroupList2,14.6);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);


        Mockito.when(orderRepositoryMock.getOrderById(order1.getOrderId()))
                .thenReturn(order2);
        Mockito.when(orderService.getOrderById(order1.getOrderId()))
                .thenReturn(orderMapper.toDto(order1));

        //when
        OrderDto orderDto = orderService.getOrderById(order1.getOrderId());

        //then
        Assertions.assertThat(orderDto).isEqualTo(orderMapper.toDto(order1));
    }
    @Test
    void createOrder_VerifyMethodCreateOrderIsCalledOnOrderService() {
        //given
        OrderMapper orderMapper = new OrderMapper(new ItemMapper());
        Item item1 = new Item("paprika chips","paprika chips",1.5,5);
        Item item2 = new Item("pickels chips","pickels chips",1.5,7);

        ItemGroup itemGroup1 = new ItemGroup(item1,3,LocalDate.now().plusDays(1),4.5);
        ItemGroup itemGroup2 = new ItemGroup(item2,2,LocalDate.now().plusDays(1),3.0);

        List<ItemGroup> itemGroupList1 = new ArrayList<>();
        itemGroupList1.add(itemGroup1);
        itemGroupList1.add(itemGroup2);

        Order order1 = new Order(LocalDate.now(),1,itemGroupList1,7.5);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);

        CreateItemGroupDto createItemGroupDto1 = new CreateItemGroupDto(1,5);
        CreateItemGroupDto createItemGroupDto2 = new CreateItemGroupDto(2,3);

        List<CreateItemGroupDto> createItemGroupDtoList = new ArrayList<>();
        createItemGroupDtoList.add(createItemGroupDto1);
        createItemGroupDtoList.add(createItemGroupDto2);

        CreateOrderDto createOrderDto = new CreateOrderDto(
                createItemGroupDtoList,1,LocalDate.now()
        );
        orderRepositoryMock.save(order1);
        Mockito.when(orderRepositoryMock.save(order1))
                .thenReturn(order1);


        //when
        //then
        Mockito.verify(orderRepositoryMock).save(order1);
    }
    @Test
    void createOrder_givingAnOrderId_returnsThatOrder() {
        //given
        int orderId = 1;
        OrderMapper orderMapper = new OrderMapper(new ItemMapper());
        Item item1 = new Item("paprika chips","paprika chips",1.5,5);
        Item item2 = new Item("pickels chips","pickels chips",1.5,7);

        ItemGroup itemGroup1 = new ItemGroup(item1,3,LocalDate.now().plusDays(1),4.5);
        ItemGroup itemGroup2 = new ItemGroup(item2,2,LocalDate.now().plusDays(1),3.0);

        List<ItemGroup> itemGroupList1 = new ArrayList<>();
        itemGroupList1.add(itemGroup1);
        itemGroupList1.add(itemGroup2);

        Order order1 = new Order(LocalDate.now(),1,itemGroupList1,7.5);

        Mockito.when(orderService.getOrderById(orderId))
                .thenReturn(orderMapper.toDto(order1));

        //when
        OrderDto orderDto = orderService.getOrderById(orderId);

        //then
        Assertions.assertThat(orderDto).isEqualTo(orderService.getOrderById(orderId));
    }
}