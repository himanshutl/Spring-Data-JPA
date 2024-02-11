package com.udemy.SpringDataJPA.Repository;


import com.udemy.SpringDataJPA.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void paginationImplementation(){
        int pageNo = 0;
        int pageSize = 2;

        // create pageable object using PageRequest class
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // provide pageable object to  findAll method from SimpleJpaRepository
        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products =  page.getContent();
        products.stream().forEach(product -> System.out.println(product.getName()));

        int totalPages = page.getTotalPages();
        System.out.println(totalPages);

        long totalItems = page.getTotalElements();
        System.out.println(totalItems);

        int numberOfElements = page.getNumberOfElements();
        System.out.println(numberOfElements);
    }


    @Test
    void sortingImplementation(){
        String sortBy = "price";

        String sortDirection = "desc";
        Sort sort1 = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        List<Product> sortedProductsList = productRepository.findAll(sort1);

        List<Product> productsSortByPriceAsc = productRepository.findAll(Sort.by(sortBy).ascending());
        List<Product> productsSortedByPriceDesc = productRepository.findAll(Sort.by("name").ascending());
        productsSortByPriceAsc
                .forEach(product -> System.out.println(product));

        productsSortedByPriceDesc
                .forEach(product -> System.out.println(product));
    }

    @Test
    void sortingByMultipleFieldsMethod(){
        String sortByName = "name";
        String sortByPrice = "price";

        String sortDir = "desc";
        Sort nameSort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortByName).ascending(): Sort.by(sortByName).descending();

        Sort priceSort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortByPrice).ascending(): Sort.by(sortByPrice);

        Sort groupSort = nameSort.and(priceSort);

        List<Product> sortedProducts = productRepository.findAll(groupSort);
        sortedProducts
                .forEach(product -> System.out.println(product));
    }

    @Test
    void sortingAndPaginationMethod(){
        String sortByPrice = "price";
        String sortByDateCreated = "dateCreated";

        String sortDir = "desc";
        Sort priceSort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortByPrice).ascending(): Sort.by(sortByPrice).descending();

        Sort dateSort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortByDateCreated).ascending(): Sort.by(sortByDateCreated).descending();

        Sort groupSort = dateSort.and(priceSort);
        int pageNo = 0;
        int pageSize = 3;
        Pageable pageable = PageRequest.of(pageNo, pageSize, groupSort);

        Page<Product> page = productRepository.findAll(pageable);
        List<Product> productsByDateByPriceDesc = page.getContent();
        productsByDateByPriceDesc
                .stream()
                .forEach(product -> System.out.println(product.toString()));
    }
}


