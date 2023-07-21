package com.product.product_frontend;

import com.product.product_frontend.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
@EnableWebMvc
public class ProductConfiguration  {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(Product.builder().id(1).name("Samsung NEO QLED TV").description("55 inch 2022 model").price(150000).imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0lZLHGS5Lo-tBrPgAhhs2b_vi78bYMbUoqw&usqp=CAU").build());
        products.add(Product.builder().id(2).name("LG Front Door Washing Machine").description("9kg 5 star model").price(150000).imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzXYd9QG494vqNiVwO6T2p--Uk-0UXnyq1TwivXZ-oVA&s").build());
        return products;
    }
}
