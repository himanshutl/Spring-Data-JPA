package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Address;
import com.udemy.SpringDataJPA.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneBidirectionalMappingTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void saveAddressMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("1000BCD");
        order.setTotalQuantity(2);
        order.setTotalPrice(new BigDecimal(2000));
        order.setStatus(true);

        Address address = new Address();
        address.setStreet("MP");
        address.setPincode(201209);
        address.setCity("MGhaziabad");
        address.setState("UP");
        address.setCountry("India");

        order.setBillingAddress(address);

        address.setOrder(order);
        addressRepository.save(address);
    }

    @Test
    void updateAddressMethod(){
        Address address = addressRepository.findById(1L).get();
        address.setPincode(209001);

        address.getOrder().setStatus(false);
        addressRepository.save(address);
    }

    @Test
    void getAddressMethod(){
        Address address = addressRepository.findById(1L).get();
        System.out.println(address.toString());
        System.out.println(address.getOrder().toString());
    }

}
