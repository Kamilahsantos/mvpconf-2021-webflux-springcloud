package com.mvpconf2021.customerapi.controller;

import com.mvpconf2021.customerapi.model.Customer;
import com.mvpconf2021.customerapi.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        log.info("Cadastrando um novo cliente com as informações [{}]", customer);
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Customer> findCustomerById(@PathVariable String id) {
        log.info("Buscando cliente com o id [{}]", id);
        return customerService.findCustomerById(id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Customer> getAllCustomers() {
        log.info("Listando todos os customers cadastrados");
        return customerService.findAllCustomers();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable(value = "id") String id,
                                                         @RequestBody Customer customer) {
        return customerService.updateCustomer(customer, id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCustomerById(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }
}
