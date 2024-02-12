package com.udemy.SpringDataJPA.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String imageUrl;

    private BigDecimal price;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public OrderItem() {
    }

    public OrderItem(String imageUrl, BigDecimal price, int quantity, Product product) {
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && Objects.equals(imageUrl, orderItem.imageUrl) && Objects.equals(price, orderItem.price) && Objects.equals(product, orderItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, price, quantity, product);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
