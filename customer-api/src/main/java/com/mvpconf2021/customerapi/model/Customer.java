package com.mvpconf2021.customerapi.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Document(collection = "customer")
public class Customer {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;
}
