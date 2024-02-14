package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
