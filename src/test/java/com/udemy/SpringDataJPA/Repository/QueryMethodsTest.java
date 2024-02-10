package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class QueryMethodsTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod() {
        Product product = productRepository.findByName("updated product");
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findProductByIdMethod() {
        Long id = 7L;
        Product product = productRepository.findProductById(id).get();
        System.out.println(product.toString());
        System.out.println(product.getId());

    }

    @Test
    void findByNameAndDescriptionMethod(){
        Collection<Product> productList = productRepository.
                findByNameAndDescription("test product", "test description");
        productList.stream()
                .forEach(product -> System.out.println(product.getName()));
    }

    @Test
    void findDistinctByNameMethod(){
        Product product = productRepository.findDistinctByName("updated product");
        System.out.println(product.getName());
    }

    @Test
    void findByPriceGreaterThanMethod(){
        BigDecimal price = new BigDecimal(150);
        List<Product> products = productRepository.findByPriceGreaterThan(price);
        products
                .forEach(product -> System.out.println(product.getName()));
    }

    @Test
    void  findByPriceLessThanMethod(){
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(250));
        products.forEach(product -> System.out.println(product.getName() +" "+product.getPrice()));
    }

    @Test
    void findByNameContainingIgnoreCaseOOrderByPriceMethod(){
        List<Product> products = productRepository
                .findByNameContainingIgnoreCaseOrderByPriceAsc("product");
        products.forEach(product -> System.out.println(product.getName() +" "+product.getPrice()));
    }

    @Test
    void findByDateCreatedBetweenMethod(){
        /*
        * this method returns all products created between 2024-02-10 11:39 and 2024-02-10 11:40
        */
        List<Product> products = productRepository
                .findByDateCreatedBetween(
                        LocalDateTime
                                .of(2024, 2, 10, 11, 39),
                        LocalDateTime
                                .of(2024, 2, 10, 12, 40)
                );
        products.forEach(product -> System.out.println(product.getName() +" "+product.getPrice()));
    }
}