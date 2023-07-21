package com.product.product_frontend;

import com.product.product_frontend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

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
    public String showProductsPage(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Map<String, String> params, Model model) {
        String url2 = String.format(url+"/api/products", serverAddress, serverPort);
        System.out.println(url2);
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
               url2,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        model.addAttribute("products", responseEntity.getBody());
        model.addAttribute("user", userDetails);
        return "products";
    }

    @GetMapping("/products/{id}")
    public String showProductPage(@AuthenticationPrincipal UserDetails userDetails, @PathVariable long id, @RequestParam Map<String, String> params, Model model) {
        ResponseEntity<Product> responseEntity = restTemplate.exchange(
                String.format(url+"/api/products/"+id, serverAddress, serverPort),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Product>() {}
        );
        model.addAttribute("product", responseEntity.getBody());
        model.addAttribute("user", userDetails);
        return "product";
    }



}
