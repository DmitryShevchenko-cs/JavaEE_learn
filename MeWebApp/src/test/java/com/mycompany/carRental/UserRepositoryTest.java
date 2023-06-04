package com.mycompany.carRental;

import com.mycompany.carRental.Entities.User;
import com.mycompany.carRental.Repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void TestAddNew(){
        User user = new User();
        user.setEmail("Dmitry2@gmail.com");
        user.setName("Dmitry");
        user.setSurname("Shevchenko");
        user.setPhoneNumber("1506012477");

        User saveduser = repo.save(user);
        Assertions.assertThat(saveduser).isNotNull();
        Assertions.assertThat(saveduser.getId()).isGreaterThan(0);
    }
    @Test
    public void TestAddNew_AlreadyUsedEmail_ThrowException(){

        User user = new User();
        user.setEmail("Dmitry@gmail.com");
        user.setName("Dmitry");
        user.setSurname("Shevchenko");
        user.setPhoneNumber("0506012477");

        Assertions.assertThatThrownBy(() -> {repo.save(user);})
                .isInstanceOf(Exception.class);
    }
    @Test
    public void TestDelUser_UserFound_DeletedUser(){
        User user = repo.findById(3).get();
        repo.deleteById(user.getId());
        Assertions.assertThat(repo.findById(user.getId())).isEmpty();
    }
    @Test
    public void testListAll(){
        Iterable<User> allUsers = repo.findAll();
        Assertions.assertThat(allUsers).hasSizeGreaterThan(0);
        for (var user : allUsers) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
       var user = repo.findById(3).get();
       user.setName("Alex");
       user.setSurname("Shev");

       user = repo.save(user);

        Assertions.assertThat(user.getName()).isEqualTo("Alex");
        Assertions.assertThat(user.getSurname()).isEqualTo("Shev");
    }

}
