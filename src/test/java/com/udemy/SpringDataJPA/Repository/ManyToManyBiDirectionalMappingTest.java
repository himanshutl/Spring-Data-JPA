package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Role;
import com.udemy.SpringDataJPA.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ManyToManyBiDirectionalMappingTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void saveRole(){
        User user = new User();
        user.setFirstName("himanshu");
        user.setLastName("verma");
        user.setEmail("himanshu@gmail.com");
        user.setPassword("secret");

        User user2 = new User();
        user.setFirstName("himanshu2");
        user.setLastName("verma2");
        user.setEmail("himanshu2@gmail.com");
        user.setPassword("secret2");

        Role admin = new Role();
        admin.setName("ROLE_ADMIN");
        admin.getUsers().add(user);

        Role customer = new Role();
        admin.setName("ROLE_CUSTOMER");

        admin.getUsers().addAll(List.of(user, user2));

        user.getRoles().addAll(List.of(admin, customer));

        user2.getRoles().addAll(List.of(admin, customer));

        customer.getUsers().addAll(List.of(user, user2));

        roleRepository.save(admin);
        roleRepository.save(customer);
    }
}
