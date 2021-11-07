package com.mvpconf2021.orderapi.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class CustomerResponse {


    private String id;

    private String name;

    private String address;
}
