package com.product.product_frontend;

import com.product.product_frontend.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

@Configuration
@EnableWebMvc
@EnableWebSecurity
public class ProductConfiguration {

    @Bean
    SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/**")
                .authenticated()
                .anyRequest()
                .permitAll();
        http.formLogin()
                .and()
                .logout();
        return http.build();
    }
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
