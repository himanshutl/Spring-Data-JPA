package com.udemy.SpringDataJPA.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user",
        uniqueConstraints = @UniqueConstraint(
                columnNames = "email"
        )
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}
