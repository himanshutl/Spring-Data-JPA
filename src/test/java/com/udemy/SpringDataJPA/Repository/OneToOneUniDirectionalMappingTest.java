package com.udemy.SpringDataJPA.Repository;
import com.udemy.SpringDataJPA.entity.Address;
import com.udemy.SpringDataJPA.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneUniDirectionalMappingTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setTotalPrice(new BigDecimal(1000));
        order.setStatus(true);

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
    void updateOrderMethod(){
        Order order = orderRepository.findByOrderTrackingNumber("1000ABC");
        order.setStatus(true);

        order.getBillingAddress().setPincode(201009);

        orderRepository.save(order);
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(2L);
    }

    @Test
    void getOrderMethod(){
        System.out.println(orderRepository.findById(52L).get());

    }
}
