package com.udemy.SpringDataJPA.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@NamedQueries(
        {
                @NamedQuery(
                        name = "Product.findByPrice",
                        query = "select p from Product p where p.price = ?1"
                ),
                @NamedQuery(
                        name = "Product.findBySku",
                        query = "select p from Product p where p.sku = :sku"
                )

        }

)
@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "Product.findByDescription",
                        query = "select * from products p where p.description = ?1 ",
                        resultClass = Product.class
                ),
                @NamedNativeQuery(
                        name = "Product.findByStatus",
                        query = "select * from products p where p.active = :active",
                        resultClass = Product.class
                )

        }
)
@Entity
@Table(name = "products",
        schema = "udemy_springdatajpa",
        uniqueConstraints = {
            @UniqueConstraint(name = "sku_unique", columnNames = "sku"),
        }
)
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_generator")
    @SequenceGenerator(
            name = "product_generator",
            sequenceName = "product_sequence",
            allocationSize = 1)
    private Long id;

    @Column(name = "stock_keeping_unit", nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @ManyToOne()
    @JoinColumn(name="fk_category_id", referencedColumnName = "pk_category_id")
    private ProductCategory productCategory;

    public Product(String sku, String name, String description, BigDecimal price, boolean active, String imageUrl) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Objects.equals(id, product.id) && Objects.equals(sku, product.sku);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku);
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", active=" + active +
                ", imageUrl='" + imageUrl + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
