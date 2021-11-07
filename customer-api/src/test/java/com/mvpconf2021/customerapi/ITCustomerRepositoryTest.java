package com.mvpconf2021.customerapi;

import com.mvpconf2021.customerapi.mock.CustomerMock;
import com.mvpconf2021.customerapi.model.Customer;
import com.mvpconf2021.customerapi.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ITCustomerRepositoryTest {

    @MockBean
    private CustomerRepository customerRepository;


    @Autowired
    private WebTestClient webTestClient;

    private final Customer customer = CustomerMock.customer();


    @BeforeEach
    public void setUp() {
        BDDMockito.when(customerRepository.findAll())
                .thenReturn(Flux.just(customer));

        BDDMockito.when(customerRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(customer));

        BDDMockito.when(customerRepository.delete(ArgumentMatchers.any(Customer.class)))
                .thenReturn(Mono.empty());


    }

    @Test
    @DisplayName("Deve listar todas os clientes")
    public void shouldListAllCustomersSuccessfully() {
        webTestClient
                .get()
                .uri("/customers")
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    @DisplayName("Deve retornar uma response válida  ao listar todas os clientes")
    public void ShouldReturnAValidResponseWhenListAllCustomers() {
        webTestClient
                .get()
                .uri("/customers")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo(customer.getId())
                .jsonPath("$.[0].name").isEqualTo(customer.getName())
                .jsonPath("$.[0].address").isEqualTo(customer.getAddress());
    }

    @Test
    @DisplayName("Deve buscar e retornar um cliente  pelo id")
    public void shouldFindAndReturnACustomerByIdSuccessfully() {
        webTestClient
                .get()
                .uri("/customers/{id}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class)
                .isEqualTo(customer);
    }

    @Test
    @DisplayName("Deve excluir um customer pelo id")
    public void shouldDeleteAnCustomerSuccessfully() {
        webTestClient
                .delete()
                .uri("/customers/{id}", 1)
                .exchange()
                .expectStatus().isNoContent();
    }
}
