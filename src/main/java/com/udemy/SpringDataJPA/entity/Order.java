package com.udemy.SpringDataJPA.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorHSQLDBDatabaseImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Orders",
        schema = "udemy_springdatajpa",
        uniqueConstraints = {
        @UniqueConstraint(name = "tracking number", columnNames = "order_tracking_number")
        }
)
public class Order {
    @Id
    @Column(name = "pk_order_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence")
    @SequenceGenerator(
            name = "order_generator",
            sequenceName = "order_sequence",
            allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderTrackingNumber;

    @Column(nullable = false)
    private int totalQuantity;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    private boolean status;

    @CreationTimestamp
    private LocalDate dateCreated;

    @CreationTimestamp
    private LocalDate dateUpdated;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "fk_order_id", referencedColumnName = "pk_order_id")
    private Set<OrderItem> orderItems = new HashSet<>();

    public BigDecimal getTotalAmount(){
        BigDecimal amount = new BigDecimal(0);
        for(OrderItem orderItem: orderItems){
            amount = amount
                    .add(orderItem.getPrice());
        }
        return amount;
    }

    public Order(String orderTrackingNumber, int totalQuantity, BigDecimal totalPrice, boolean status, Address billingAddress, Set<OrderItem> orderItems) {
        this.orderTrackingNumber = orderTrackingNumber;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.billingAddress = billingAddress;
        this.orderItems = orderItems;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return totalQuantity == order.totalQuantity && Objects.equals(orderTrackingNumber, order.orderTrackingNumber) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(dateCreated, order.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderTrackingNumber, totalQuantity, totalPrice, dateCreated);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTrackingNumber='" + orderTrackingNumber + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", billingAddress=" + billingAddress +
                '}';
    }
}
