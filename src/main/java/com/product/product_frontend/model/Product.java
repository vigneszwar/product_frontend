package com.product.product_frontend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private String description;
    private long price;
    private String imageUrl;
}
