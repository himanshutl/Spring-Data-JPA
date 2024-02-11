package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NativeSQLQueriesTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void findByNameOrDescriptionSQLIndexQueryMethod(){
        Product product = productRepository
                .findByNameOrDescriptionSQLIndexQuery("updated product", "update product");
        System.out.println(product.getName() +" "+product.getPrice());
    }

    @Test
    void findByNameOrDescriptionSQLNamedQueryMethod(){
        Product product = productRepository
                .findByNameOrDescriptionSQLNamedQuery("test product 2", "test description 2");
        System.out.println(product.getName() +" "+product.getPrice());
    }


}
