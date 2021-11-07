package com.mvpconf2021.deliveryapi.client;

import com.mvpconf2021.deliveryapi.response.OrderToDeliveryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class OrderToDeliveryClient {


    private final WebClient webClient;

    public OrderToDeliveryClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("http://localhost:8082").build();
    }

    public Flux<OrderToDeliveryResponse> ListOrdersToDelivery() {
        log.info("listando todas as ordens para serem encaminhadas ao setor de delivery");
        return webClient.get()
                .uri("/orders")
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(OrderToDeliveryResponse.class);
    }
}
