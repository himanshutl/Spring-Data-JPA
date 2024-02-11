package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class NamedQueryJPQLTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void findByPriceMethod(){
        Product product = productRepository.findByPrice(new BigDecimal(400));
        System.out.println(product.getName() +" "+product.getPrice());
    }

    @Test
    void findBySkuMethod(){
        Product product = productRepository.findBySku("test sku");
        System.out.println(product.getName() +" "+product.getPrice());
    }
}
