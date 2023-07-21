package com.product.product_frontend;

import com.product.product_frontend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ProductPageController {

    @Value("${api.address}")
    private String serverAddress;
    @Value("${api.port}")
    private String serverPort;

    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public ProductPageController() {
        url = "http://%s:%s";
    }

    @GetMapping("/products")
    public String showProductPage(Model model) {
        String url2 = String.format(url+"/api/products", serverAddress, serverPort);
        System.out.println(url2);
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
               url2,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        model.addAttribute("products", responseEntity.getBody());
        return "products";
    }

    @GetMapping("/products/{id}")
    public String showProductPage(@PathVariable long id, Model model) {
        ResponseEntity<Product> responseEntity = restTemplate.exchange(
                String.format(url+"/api/products/"+id, serverAddress, serverPort),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Product>() {}
        );
        model.addAttribute("product", responseEntity.getBody());
        return "product";
    }

    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable("id") long id, Model model) {
        ResponseEntity<Product> responseEntity = restTemplate.exchange(
                String.format(url+"/api/products/"+id, serverAddress, serverPort),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Product>() {}
        );
        model.addAttribute("product", responseEntity.getBody());
        return "product-edit-form";
    }

    }
