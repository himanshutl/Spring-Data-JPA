package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Address;
import com.udemy.SpringDataJPA.entity.Order;
import com.udemy.SpringDataJPA.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@SpringBootTest
public class OneToManyUniDirectionalMappingTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("100ABC");
        order.setStatus(true);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(productRepository.findById(1L).get());
        orderItem1.setQuantity(2);
        orderItem1
                .setPrice(
                        orderItem1.getProduct().getPrice()
                                .multiply(new BigDecimal(orderItem1.getQuantity())));
        orderItem1.setImageUrl("image.png");
        order.getOrderItems().add(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(productRepository.findById(2L).get());
        orderItem2.setQuantity(3);
        orderItem2
                .setPrice(
                        orderItem2.getProduct().getPrice()
                                .multiply(new BigDecimal(orderItem2.getQuantity())));
        orderItem2.setImageUrl("image.png");
        order.getOrderItems().add(orderItem2);

        order.setTotalQuantity(2);
        order.setTotalPrice(order.getTotalAmount());

        Address address = new Address();
        address.setStreet("SV");
        address.setPincode(201009);
        address.setCity("Ghaziabad");
        address.setState("UP");
        address.setCountry("India");

        order.setBillingAddress(address);
        orderRepository.save(order);
    }

    @Test
    void getOrderMethod(){
        Order order = orderRepository.findById(2L).get();
        for (OrderItem item: order.getOrderItems())
            System.out.println(item.getProduct().getName());
    }

}
