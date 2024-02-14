package com.udemy.SpringDataJPA.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles",
        uniqueConstraints =
        @UniqueConstraint(
                columnNames = "name",
                name = "name"
        )
)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence")
    @Column(name = "pk_role_id")
    private Long id;

    @Column(unique = true)
    private String name;


}
