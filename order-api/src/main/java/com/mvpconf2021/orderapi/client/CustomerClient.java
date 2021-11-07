package com.mvpconf2021.orderapi.client;

import com.mvpconf2021.orderapi.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "customer", url = "http://localhost:8081/customers")
public interface CustomerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}" )
    CustomerResponse findCustomerById(@PathVariable(name = "id") String id);
}
