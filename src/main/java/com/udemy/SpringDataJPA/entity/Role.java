package com.udemy.SpringDataJPA.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "roles"
    )
    private Set<User> users = new HashSet<>();
}
