package com.eurder.eurder.api.order;

import com.eurder.eurder.api.item.dto.CreateItemGroupDto;
import com.eurder.eurder.api.order.dto.CreateOrderDto;
import com.eurder.eurder.api.order.dto.OrderDto;
import com.eurder.eurder.domain.customer.Customer;
import com.eurder.eurder.domain.customer.CustomerRepository;
import com.eurder.eurder.domain.customer.CustomerRepositoryJpa;
import com.eurder.eurder.domain.item.*;
import com.eurder.eurder.domain.order.Order;
import com.eurder.eurder.domain.order.OrderRepository;
import com.eurder.eurder.service.order.OrderMapper;
import com.eurder.eurder.service.order.OrderService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("disable-keycloak")
@EnableAutoConfiguration
@AutoConfigureTestDatabase
class OrderControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemGroupRepository itemGroupRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderService orderService;

    @Test
    void whenThereIsOneOrderInTheRepository_thenICanRetrieveThisOrderById() {

        // GIVEN
        // WHEN
        OrderDto orderDto = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@eurder.com","123")
                .contentType(ContentType.JSON)
                //.header(new Header("Authorization", "Basic username:password"))
                //.auth().preemptive().basic("username", "password")
                .log().all()
                .when()
                .port(port)
                .get("/orders/" + 1) // http://localhost:???/orders/1
                // THEN
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value()) // status 200
                .extract()
                .as(OrderDto.class); // Get a contact from the system
        //THEN
        Assertions.assertThat(orderDto).isEqualTo(orderMapper.toDto(orderRepository.findById(1).get()));
    }
//    @Test
//    void whenIPostAnOrder_thenTheRepositoryContainsThisOrder() throws CloneNotSupportedException {
//        Item item1 = new Item("Ping pong net","A net to install on a ping pong table",15.0,2);
//        Item item2 = new Item("Ping pong ballen","Ping pong ballen (per 6)",2,2);
//        itemRepository.save(item1);
//        itemRepository.save(item2);
//
//        Customer customer1 = new Customer("Louis","Koppens","louis.Koppens@gmail.com","123","BeCentral 2","123456");
//        customerRepository.save(customer1);
//
//        CreateItemGroupDto createItemGroupDto1 = new CreateItemGroupDto(1,5);
//        CreateItemGroupDto createItemGroupDto2 = new CreateItemGroupDto(2,3);
//
//        List<CreateItemGroupDto> createItemGroupDtoList = new ArrayList<>();
//        createItemGroupDtoList.add(createItemGroupDto1);
//        createItemGroupDtoList.add(createItemGroupDto2);
//
//        CreateOrderDto createOrderDto = new CreateOrderDto(
//                createItemGroupDtoList,customer1.getId(),LocalDate.now()
//        );
//        orderService.createOrder(createOrderDto);
//
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(createOrderDto)
//                .when()
//                .port(port)
//                .post("/orders/register") // http://localhost:???/orders/register
//                .then()
//                .log().all();
//                //.assertThat()
//                //.statusCode(HttpStatus.CREATED.value());
//
//        Assertions.assertThat(orderRepository.findById(2)).isNotEmpty();
//    }
//    @Test
//    void whenTheRepositoryIsEmpty_thenIReceiveA404WhenRequestingAnOrderById() {
//        RestAssured
//                // GIVEN
//                .given()
//                .auth()
//                .preemptive()
//                .basic("admin@eurder.com","123")
//                .contentType(ContentType.JSON)
//                // WHEN
//                .when()
//                .port(port)
//                .get("/orders/100") // http://localhost:???/orders/100
//                // THEN
//                .then()
//                .log().all()
//                .assertThat()
//                .statusCode(HttpStatus.NOT_FOUND.value());
//
//        Assertions.assertThat(orderRepository.getAllOrders()).isEmpty();
//
//    }
//    @Test
//    void whenTheRepositoryContainsTwoOrders_thenICanRetrieveThemViaTheAPI(){
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
//
//        orderRepository.save(order1);
//        orderRepository.save(order2);
//
//        //when
//        List<Order> list = RestAssured
//                .given()
//                .auth()
//                .preemptive()
//                .basic("admin@eurder.com","123")
//                .contentType(ContentType.JSON)
//                .when()
//                .port(port)
//                .get("orders") // http://localhost:???/orders
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .extract()
//                .body()
//                .jsonPath()
//                .getList(".", Order.class);
//
//        Assertions.assertThat(list).containsExactlyInAnyOrder(order1, order2);
//    }

}