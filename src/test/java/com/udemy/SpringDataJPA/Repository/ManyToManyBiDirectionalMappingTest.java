package com.udemy.SpringDataJPA.Repository;

import com.udemy.SpringDataJPA.entity.Role;
import com.udemy.SpringDataJPA.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManyToManyBiDirectionalMappingTest {

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
        user2.setFirstName("himanshu2");
        user2.setLastName("verma2");
        user2.setEmail("himanshu2@gmail.com");
        user2.setPassword("secret2");

        Role admin = new Role();
        admin.setName("ROLE_ADMIN");

        admin.getUsers().add(user);
        admin.getUsers().add(user2);

        user.getRoles().add(admin);
        user2.getRoles().add(admin);

        roleRepository.save(admin);
    }
}
