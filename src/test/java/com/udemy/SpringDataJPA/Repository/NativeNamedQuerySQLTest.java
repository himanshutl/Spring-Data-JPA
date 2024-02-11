package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NativeNamedQuerySQLTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void findByDescriptionMethod(){
        Product product = productRepository.findByDescription("update product");
        System.out.println(product.getName() +" "+product.getDescription());
    }

    @Test
    void findByStatusMethod(){
        List<Product> products = productRepository.findByActive(true);
        products
                .forEach(product -> System.out.println(product.getName() +" "+product.isActive()));
    }
}
