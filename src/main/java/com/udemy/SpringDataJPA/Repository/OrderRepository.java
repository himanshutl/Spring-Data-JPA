package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderTrackingNumber(String orderTrackingNumber);
}
