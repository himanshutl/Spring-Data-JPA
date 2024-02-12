package com.udemy.SpringDataJPA.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence")
    @SequenceGenerator(
            name = "address_generator",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @Column(name = "address_id")
    private Long id;
    private String street;
    private int pincode;
    private String city;
    private String state;
    private String country;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_order_id", referencedColumnName = "pk_order_id")
    private Order order;

    public Address(String street, int pincode, String city, String state, String country, Order order) {
        this.street = street;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.country = country;
        this.order = order;
    }

    public Address(String street, int pincode, String city, String state, String country) {
        this.street = street;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Address() {
    }



    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return pincode == address.pincode && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, pincode, city, state, country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", pincode=" + pincode +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
