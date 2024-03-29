package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    List<Product> findByPriceLessThan(BigDecimal price);

    List<Product> findByNameContainingIgnoreCaseOrderByPriceAsc(String name);

    List<Product> findByNameLike(String name);

    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Product> findByNameIn(List<String> names);

    List<Product> findFirst2ByOrderByPriceDesc();

    List<Product> findTop2ByOrderByDateCreatedDesc();

    // define JPQL query using @Query with index or position parameter
    @Query("select p from Product p where p.name = ?1 OR p.description = ?2")
    List<Product> findByNameOrDescription(String name, String description);

    //define JPQL query with @Query annotation with named parameters
    @Query("select p from Product p where p.name = :name or p.description = :description")
    Product findByNameOrDescriptionJPQLNamedParam(@Param("name") String name,
                                                  @Param("description")String description);

    //using native sql query using @Query, value and nativeQuery=true
    @Query(value = "select * from products p where p.name = ?1 or p.description = ?2", nativeQuery = true)
    Product findByNameOrDescriptionSQLIndexQuery(String name, String description);

    //using native sql query using @Query, value and nativeQuery=true
    @Query(value = "select * from products p where p.name = :name or p.description = :description", nativeQuery = true)
    Product findByNameOrDescriptionSQLNamedQuery(
            @Param("name") String name, @Param("description")String description);

    /*
    *using JPQL @NamedQuery to find Product by price
    * method names is same as the value="" in @NamedQuery
    */
    Product findByPrice(BigDecimal price);

    /*
    * using JPQL @NamedQuery to find Product by sku
    * method name is same as value
    */
    Product findBySku(String sku);

    /*
    * using SQL NativeNamedQuery to find product by description
    */
    @Query(nativeQuery = true)
    Product findByDescription(String description);
    @Query(nativeQuery = true)
    List<Product> findByActive(@Param("status") boolean status);
}
