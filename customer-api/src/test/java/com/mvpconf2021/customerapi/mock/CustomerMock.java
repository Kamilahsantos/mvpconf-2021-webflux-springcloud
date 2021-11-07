package com.mvpconf2021.customerapi.mock;

import com.mvpconf2021.customerapi.model.Customer;

public class CustomerMock {

    public static Customer customer(){
        return Customer.builder()
                .id("0b8c80cf-28c5-41f5-a3bf-a4ab7885bd87")
                .address("rua do teste")
                .name("customer de teste")
                .build();
    }
}
