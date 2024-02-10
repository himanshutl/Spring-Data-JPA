package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        //create product
        Product product = new Product();
        product.setName("test product");
        product.setDescription("test description");
        product.setSku("test sku");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("test url");

        //save product in the database
        Product savedProduct = productRepository.save(product);

        //display the product info
        System.out.println(savedProduct.getId());
        System.out.println(savedProduct.toString());
    }

    @Test
    void updateUsingSaveMethod(){
        //find or retrieve an entity from the database
        Long id = 1L;
        Product product = productRepository.findById(id).get();

        //update the entity
        product.setName("updated product");
        product.setDescription("update product");

        //save the entity
        productRepository.save(product);
    }

    @Test
    void findByIdMethod(){
        Long id = 1L;

        Product product = productRepository.findById(id).get();
        System.out.println(product.toString());
        System.out.println(product.getId());

    }

    @Test
    void saveAllMethod(){
        Product product = new Product();
        product.setName("test product 2");
        product.setDescription("test description 2");
        product.setSku("test sku 2");
        product.setPrice(new BigDecimal(200));
        product.setActive(true);
        product.setImageUrl("test url 2");

        Product product2 = new Product();
        product2.setName("test product 3");
        product2.setDescription("test description 3");
        product2.setSku("test sku 3");
        product2.setPrice(new BigDecimal(400));
        product2.setActive(true);
        product2.setImageUrl("test url 3");

        productRepository.saveAll(List.of(product, product2));
    }

    @Test
    void findAllMethod() {
        List<Product> products = productRepository.findAll();

        products.forEach(
                product -> System.out.println(product.getName())
        );
    }

    @Test
    //does job in 503ms
    void deleteByIdMethod(){
        Long id = 3L;

        productRepository.deleteById(id);
    }

    @Test
    // does job in 453ms
    void deleteMethod(){
        Long id = 2L;
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Test
    void deleteAllMethod(){

        productRepository.deleteAll();
    }

    @Test
    void deleteAllById(){
        Product p1 = productRepository.findById(4L).get();
        Product p2 = productRepository.findById(5L).get();

        productRepository.deleteAllById(List.of(p1.getId() ,p2.getId()));
    }

    @Test
    void countMethod(){
        Long count = productRepository.count();
        System.out.println(count);
    }

    @Test
    void existsByIdMethod(){
        boolean b = productRepository.existsById(1L);
        System.out.println(b);
    }
}

