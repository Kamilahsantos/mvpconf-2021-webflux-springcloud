package com.mvpconf2021.deliveryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeliveryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApiApplication.class, args);
	}

}
