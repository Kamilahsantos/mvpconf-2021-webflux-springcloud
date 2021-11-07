package com.mvpconf2021.orderapi.service;

import com.mvpconf2021.orderapi.model.Order;
import com.mvpconf2021.orderapi.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        log.info("criando um nova ordem com as informacoes [{}]", order);
        return orderRepository.save(order);
    }

    public List<Order> listAllOrders() {
        log.info("Listando todas as ordens cadastradas na base");
        return orderRepository.findAll();
    }

    public ResponseEntity<Order> findOrderById(Long id) {
        log.info("Buscando uma ordem pelo id");
        return orderRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }
}
