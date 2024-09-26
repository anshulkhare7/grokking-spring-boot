package com.anshul.ecom.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    private String id;
    private String prodName;
    private int price;
    private String category;
}
