package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Product;
import com.udemy.SpringDataJPA.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void saveProductCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("books");
        productCategory.setCategoryDescription("books description");

        Product p1 = new Product();
        p1.setName("Core java");
        p1.setPrice(new BigDecimal(1000));
        p1.setDescription("Introduction to java programming");
        p1.setActive(true);
        p1.setSku("JAVA");
        p1.setImageUrl("java.png");
        p1.setProductCategory(productCategory);

        productCategory.getProducts().add(p1);

        Product p2 = new Product();
        p2.setName("Effective java");
        p2.setPrice(new BigDecimal(2000));
        p2.setDescription("Advanced Java programming");
        p2.setActive(true);
        p2.setSku("JAVA1");
        p2.setImageUrl("effective.png");
        p2.setProductCategory(productCategory);
        productCategory.getProducts().add(p2);

        productCategoryRepository.save(productCategory);


    }


}