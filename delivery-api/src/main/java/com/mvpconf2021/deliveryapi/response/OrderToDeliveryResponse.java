package com.mvpconf2021.deliveryapi.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class OrderToDeliveryResponse {

    private Long id;
    private String description;
    private Long amountValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String customerId;

}
