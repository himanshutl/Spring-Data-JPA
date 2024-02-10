package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*
    * returns found product by using its name as search criteria.
    * If no product is found, this method returns null.
    * */
    Product findByName(String name);
    Optional<Product> findProductById(Long id);

    /*
    * Returns Collection of product records with name and description as method parameters
    * If no match found, method returns an empty collection
    * */
    Collection<Product> findByNameAndDescription(String name, String description);

    Product findDistinctByName(String name);

    List<Product> findByPriceGreaterThan(BigDecimal price);
}
