package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
