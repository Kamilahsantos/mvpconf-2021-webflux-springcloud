package com.mvpconf2021.orderapi.controller;

import com.mvpconf2021.orderapi.client.CustomerClient;
import com.mvpconf2021.orderapi.model.Order;
import com.mvpconf2021.orderapi.response.CustomerResponse;
import com.mvpconf2021.orderapi.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Slf4j
public class OrderController {


    CustomerClient customerClient;

    OrderService orderService;

    @GetMapping("/customer-data/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerById(@PathVariable String id) {
        log.info("Buscando as informações do cliente com o id [{}] para conclusão ds ordem ", id);
        return customerClient.findCustomerById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        log.info("Criando uma nova ordem com as informações [{}]", order);
        return orderService.createOrder(order);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        log.info("Listando todas as ordens cadastradas");
        return orderService.listAllOrders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long id) {
        log.info("Buscando ordem com o id [{}]", id);
        return orderService.findOrderById(id);
    }


}
