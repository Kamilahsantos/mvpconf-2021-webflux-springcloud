package com.mvpconf2021.customerapi;


import com.mvpconf2021.customerapi.mock.CustomerMock;
import com.mvpconf2021.customerapi.model.Customer;
import com.mvpconf2021.customerapi.repository.CustomerRepository;
import com.mvpconf2021.customerapi.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private final Customer customer= CustomerMock.customer();

    @BeforeEach
    public void setUp() {

        BDDMockito.when(customerRepository.save(CustomerMock.customer()))
                .thenReturn(Mono.just(customer));

        BDDMockito.when(customerRepository.findAll())
                .thenReturn(Flux.just(customer));

        BDDMockito.when(customerRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(customer));


        BDDMockito.when(customerRepository.delete(ArgumentMatchers.any(Customer.class)))
                .thenReturn(Mono.empty());
    }


    @Test
    @DisplayName("Deve criar um cliente ")
    public void shouldCreateAnCustomerSuccessfully() {
        Customer customer = CustomerMock.customer();

        StepVerifier.create(customerService.saveCustomer(customer))
                .expectSubscription()
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve listar todas os clientes")
    public void shouldReturnAllCustomersSuccessfully() {
        StepVerifier.create(customerService.findAllCustomers())
                .expectSubscription()
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve buscar e retornar um cliente pelo id ")
    public void shouldFindByIdAndReturnAnCustomerSuccessfully() {
        StepVerifier.create(customerService.findCustomerById("0b8c80cf-28c5-41f5-a3bf-a4ab7885bd87"))
                .expectSubscription()
                .expectNext(customer)
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve deletar um cliente  com sucesso")
    public void shouldDeleteAnLadySuccessfully() {
        StepVerifier.create(customerService.deleteCustomer("0b8c80cf-28c5-41f5-a3bf-a4ab7885bd87"))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve retornar exception quando não encontrar um cliente")
    public void ShouldReturnExceptionWhenCustomerNotFound() {
        BDDMockito.when(customerRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(customerService.findCustomerById("cliente nao criada"))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

}
