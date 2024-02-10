package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPQLQueriesTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameOrDescriptionMethod(){
        List<Product> products = productRepository
                .findByNameOrDescription("updated product", "test description 2");
        products.forEach(product -> System.out.println(product.getName() +" "+product.getPrice()));
    }

    @Test
    void findByNameOrDescriptionJPQLNamedParamMethod(){
        Product product = productRepository
                .findByNameOrDescriptionJPQLNamedParam("updated product", "update product");
        System.out.println(product.getName() +" "+product.getPrice());
    }
}
