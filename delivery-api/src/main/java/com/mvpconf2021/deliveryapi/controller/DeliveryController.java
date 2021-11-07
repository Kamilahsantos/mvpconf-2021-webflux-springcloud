package com.mvpconf2021.deliveryapi.controller;

import com.mvpconf2021.deliveryapi.client.OrderToDeliveryClient;
import com.mvpconf2021.deliveryapi.response.OrderToDeliveryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@AllArgsConstructor
@RestController
@RequestMapping("/delivery")
@Slf4j
public class DeliveryController {

    OrderToDeliveryClient orderToDeliveryClient;


    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public Flux<OrderToDeliveryResponse> getAllOrdersToDelivery() {
        log.info("Listando todas as ordens a serem encaminhadas ao setor de delivery");
        return orderToDeliveryClient.ListOrdersToDelivery();
    }


}
