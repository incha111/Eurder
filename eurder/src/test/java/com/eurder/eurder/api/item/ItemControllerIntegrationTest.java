package com.eurder.eurder.api.item;

import com.eurder.eurder.domain.item.Item;
import com.eurder.eurder.domain.item.ItemRepository;
import com.eurder.eurder.domain.item.ItemRepositoryJpa;
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

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("disable-keycloak")
@EnableAutoConfiguration
@AutoConfigureTestDatabase
class ItemControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void whenThereIsOneItemInTheRepository_thenICanRetrieveThisItemById() {
        // GIVEN
        Item item1 = new Item("Ping pong net","A net to install on a ping pong table",15.0,2);
        itemRepository.save(item1);

        // WHEN
        Item item = RestAssured
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
                .get("/items/" + item1.getId()) // http://localhost:???/items/1
                // THEN
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value()) // status 200
                .extract()
                .as(Item.class); // Get a contact from the system
        //THEN
        Assertions.assertThat(item).isEqualTo(item1);
    }
    @Test
    void whenIPostAnItem_thenTheRepositoryContainsThisItem() {
        Item item1 = new Item("Ping pong net","A net to install on a ping pong table",15.0,2);
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@eurder.com","123")
                .contentType(ContentType.JSON)
                .body(item1)
                .when()
                .port(port)
                .post("/items/register") // http://localhost:???/items/register
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertThat(itemRepository.findAll()).isNotEmpty();
    }
    @Test
    void whenTheRepositoryIsEmpty_thenIReceiveA404WhenRequestingAItemById() {
        RestAssured
                // GIVEN
                .given()
                .contentType(ContentType.JSON)
                // WHEN
                .when()
                .port(port)
                .get("/items/100") // http://localhost:???/items/100
                // THEN
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());

        Assertions.assertThat(itemRepository.findAll()).isEmpty();

    }
    @Test
    void whenTheRepositoryContains2Items_thenICanRetrieveThemViaTheAPI(){
        //given
        Item item1 = new Item("Ping pong net","A net to install on a ping pong table",15.0,2);
        Item item2 = new Item("Ping pong ballen","Ping pong ballen (per 6)",2,2);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> list = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@eurder.com","123")
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .get("items") // http://localhost:???/items
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", Item.class);

        Assertions.assertThat(list).containsExactlyInAnyOrder(item1, item2);
    }
}