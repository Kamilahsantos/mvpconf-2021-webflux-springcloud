package com.mvpconf2021.customerapi.service;

import com.mvpconf2021.customerapi.model.Customer;
import com.mvpconf2021.customerapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;


    public Mono<Customer> saveCustomer(Customer customer) {
        log.info("Cadastrando um novo cliente com as informações [{}]", customer);
        return customerRepository.save(customer);
    }

    public Mono<Customer> findCustomerById(String id) {
        log.info("Buscando o cliente com o id [{}]", id);
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));

    }

    public Flux<Customer> findAllCustomers() {
        log.info("listando todos os clientes cadastrados na base");
        return customerRepository.findAll();

    }

    public Mono<ResponseEntity<Customer>> updateCustomer(Customer customer, String id) {
        log.info("atualizando o customer com o id [{}]  com as novas informações [{}]", id, customer);
        return customerRepository.findById(id)
                .flatMap(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setAddress(customer.getAddress());

                    return customerRepository.save(existingCustomer);

                }).map(updateCustomer -> new ResponseEntity<>(updateCustomer, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Mono<Void> deleteCustomer(String id) {
        log.info(" excluindo o customer que tem o id [{}]", id);
        return findCustomerById(id)
                .flatMap(customerRepository::delete);
    }
}
