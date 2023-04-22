package com.eurder.eurder.service.order;

import com.eurder.eurder.api.order.dto.OrderDto;
import com.eurder.eurder.domain.item.Item;
import com.eurder.eurder.domain.item.ItemGroup;
import com.eurder.eurder.domain.order.Order;
import com.eurder.eurder.service.Item.ItemMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class OrderMapperTest {
    OrderMapper orderMapper;

    @BeforeEach
    void setUpOrderMapperTest() {
        orderMapper = new OrderMapper(new ItemMapper());
    }

//    @Test
//    void toDto_givingAnOrder_thenReturnsAnOrderDto() {
//        //given
//        Item item1 = new Item("paprika chips","paprika chips",1.5,5);
//        Item item2 = new Item("pickels chips","pickels chips",1.5,7);
//
//        ItemGroup itemGroup1 = new ItemGroup(item1,3,LocalDate.now().plusDays(1),4.5);
//        ItemGroup itemGroup2 = new ItemGroup(item2,2,LocalDate.now().plusDays(1),3.0);
//
//        List<ItemGroup> itemGroupList1 = new ArrayList<>();
//        itemGroupList1.add(itemGroup1);
//        itemGroupList1.add(itemGroup2);
//
//        Order order = new Order(LocalDate.now(),1,itemGroupList1,7.5);
//
//        //when
//        OrderDto orderDto = orderMapper.toDto(order);
//
//        //then
//        Assertions.assertThat(orderDto).isEqualTo(orderMapper.toDto(order));
//
//    }
//
//    @Test
//    void toDto_givingAListOfOrders_thenReturnsAListOfOrderDto() {
//        //given
//        Item item1 = new Item("paprika chips","paprika chips",1.5,5);
//        Item item2 = new Item("pickels chips","pickels chips",1.5,7);
//        Item item3 = new Item("dark chocolate","dark chocolate",2.3,10);
//        Item item4 = new Item("white chocolate","white chocolate",2.7,2);
//
//        ItemGroup itemGroup1 = new ItemGroup(item1,3,LocalDate.now().plusDays(1),4.5);
//        ItemGroup itemGroup2 = new ItemGroup(item2,2,LocalDate.now().plusDays(1),3.0);
//        ItemGroup itemGroup3 = new ItemGroup(item3,4,LocalDate.now().plusDays(1),9.2);
//        ItemGroup itemGroup4 = new ItemGroup(item4,2,LocalDate.now().plusDays(1),5.4);
//
//        List<ItemGroup> itemGroupList1 = new ArrayList<>();
//        itemGroupList1.add(itemGroup1);
//        itemGroupList1.add(itemGroup2);
//
//        List<ItemGroup> itemGroupList2 = new ArrayList<>();
//        itemGroupList1.add(itemGroup3);
//        itemGroupList1.add(itemGroup4);
//
//        Order order1 = new Order(LocalDate.now(),1,itemGroupList1,7.5);
//        Order order2 = new Order(LocalDate.now(),1,itemGroupList2,14.6);
//        List<Order> orderList = new ArrayList<>();
//        orderList.add(order1);
//        orderList.add(order2);
//
//        //when
//        List<OrderDto> orderDtoList = orderMapper.toDto(orderList);
//
//        //then
//        Assertions.assertThat(orderDtoList).isEqualTo(orderMapper.toDto(orderList));
//    }
}