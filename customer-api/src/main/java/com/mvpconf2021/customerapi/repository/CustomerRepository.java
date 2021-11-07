package com.mvpconf2021.customerapi.repository;

import com.mvpconf2021.customerapi.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {


}
