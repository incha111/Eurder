package com.eurder.eurder.api.customer;

import com.eurder.eurder.domain.customer.Customer;
import com.eurder.eurder.domain.customer.CustomerRepository;
import com.eurder.eurder.domain.customer.CustomerRepositoryJpa;
import com.eurder.eurder.service.customer.CustomerService;
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
class CustomerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void getAllCustomers_whenCustomersArePresentInTheDatabase_thenReturnsThoseCustomers() {
        {
            // GIVEN
            Customer vincent = new Customer("Vincent", "Bommery", "vbo@gmail.com", "123", "Vincent's address", "Vincent's  phone");
            Customer viviane = new Customer("Viviane", "Lesage", "vle@gmail.com", "123", "Viviane's address", "Viviane's phone");
            customerRepository.save(vincent);
            customerRepository.save(viviane);

            List<Customer> list = RestAssured
                    .given()
                    .auth()
                    .preemptive()
                    .basic("admin@eurder.com", "123")
                    .contentType(ContentType.JSON)
                    .when()
                    .port(port)
                    .get("customers") // http://localhost:???/customers
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .extract()
                    .body()
                    .jsonPath()
                    .getList(".", Customer.class);

            Assertions.assertThat(list).containsExactlyInAnyOrder(vincent, viviane);
        }
    }

    @Test
    void whenThereIsOneUserInTheRepository_thenICanRetrieveThisUserById() {
        // GIVEN
        Customer customer1 = new Customer("Louis", "Koppens", "louis.Koppens@gmail.com", "123", "BeCentral 2", "123456");
        customerRepository.save(customer1);

        // WHEN
        Customer customer = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@eurder.com", "123")
                .contentType(ContentType.JSON)
                //.header(new Header("Authorization", "Basic username:password"))
                //.auth().preemptive().basic("username", "password")
                .log().all()
                .when()
                .port(port)
                .get("/customers/" + customer1.getId()) // http://localhost:???/customer/1
                // THEN
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value()) // status 200
                .extract()
                .as(Customer.class); // Get a contact from the system
        //THEN
        Assertions.assertThat(customer).isEqualTo(customer1);
    }
    @Test
    void whenACustomerRegisters_thenTheRepositoryContainsThisCustomer() {
        Customer newCustomer = new Customer("Louis","Koppens","louis.Koppens@gmail.com","123","BeCentral 2","123456");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(newCustomer)
                .when()
                .port(port)
                .post("/customers/register") // http://localhost:???/customers/register
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertThat(customerRepository.findAll()).isNotEmpty();
    }
    @Test
    void whenTheRepositoryIsEmpty_thenIReceiveA404WhenRequestingAUserById() {
        RestAssured
                // GIVEN
                .given()
                .auth()
                .preemptive()
                .basic("admin@eurder.com","123")
                .contentType(ContentType.JSON)
                // WHEN
                .when()
                .port(port)
                .get("/customers/100") // http://localhost:???/customers/100
                // THEN
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());

        Assertions.assertThat(customerRepository.findAll()).isEmpty();

    }

}